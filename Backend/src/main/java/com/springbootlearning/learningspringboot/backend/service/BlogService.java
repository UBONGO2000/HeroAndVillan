package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.BlogDto;
import com.springbootlearning.learningspringboot.backend.entity.AntiHero;
import com.springbootlearning.learningspringboot.backend.entity.Blog;
import com.springbootlearning.learningspringboot.backend.entity.Hero;
import com.springbootlearning.learningspringboot.backend.entity.Villain;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.repository.AntiHeroRepository;
import com.springbootlearning.learningspringboot.backend.repository.BlogRepository;
import com.springbootlearning.learningspringboot.backend.repository.HeroRepository;
import com.springbootlearning.learningspringboot.backend.repository.VillainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service pour la gestion des Blogs.
 * 
 * <p>Ce service fournit les opérations CRUD pour les blogs,
 * incluant la gestion des relations avec les héros, vilains et anti-héros.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class BlogService {

    private static final Logger logger = LoggerFactory.getLogger(BlogService.class);
    private final BlogRepository blogRepository;
    private final HeroRepository heroRepository;
    private final VillainRepository villainRepository;
    private final AntiHeroRepository antiHeroRepository;

    public BlogService(BlogRepository blogRepository, HeroRepository heroRepository,
                       VillainRepository villainRepository, AntiHeroRepository antiHeroRepository) {
        this.blogRepository = blogRepository;
        this.heroRepository = heroRepository;
        this.villainRepository = villainRepository;
        this.antiHeroRepository = antiHeroRepository;
    }

    /**
     * Récupère tous les blogs.
     * 
     * @return un Iterable de tous les blogs
     */
    public Iterable<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

    /**
     * Récupère un blog par son identifiant.
     * 
     * @param id l'identifiant du blog
     * @return le blog trouvé
     * @throws NotFoundException si le blog n'est pas trouvé
     */
    public Blog findBlogById(UUID id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Blog by id " + id + " was not found")
        );
    }

    /**
     * Supprime un blog par son identifiant.
     * 
     * @param id l'identifiant du blog à supprimer
     */
    @Transactional
    public void removeBlogById(UUID id) {
        blogRepository.deleteById(id);
        logger.info("Blog deleted successfully with id: {}", id);
    }

    /**
     * Crée un nouveau blog.
     * 
     * @param blogDto les données du blog à créer
     * @return le blog créé
     * @throws NotFoundException si un personnage référencé n'existe pas
     */
    @Transactional
    public Blog createBlog(BlogDto blogDto) {
        Blog blog = new Blog();

        updateBlogFromDto(blog, blogDto);

        Blog savedBlog = blogRepository.save(blog);
        logger.info("Blog created successfully with id: {}", savedBlog.getId());
        return savedBlog;
    }

    /**
     * Met à jour un blog existant.
     * 
     * @param id l'identifiant du blog à mettre à jour
     * @param blogDto les nouvelles données du blog
     * @return le blog mis à jour
     * @throws NotFoundException si le blog ou un personnage référencé n'est pas trouvé
     */
    @Transactional
    public Blog updateBlog(UUID id, BlogDto blogDto) {
        Blog blog = findBlogById(id);

        updateBlogFromDto(blog, blogDto);

        Blog updatedBlog = blogRepository.save(blog);
        logger.info("Blog updated successfully with id: {}", updatedBlog.getId());
        return updatedBlog;
    }

    /**
     * Met à jour les propriétés d'un blog à partir du DTO.
     * 
     * @param blog le blog à mettre à jour
     * @param blogDto les données source
     */
    private void updateBlogFromDto(Blog blog, BlogDto blogDto) {
        blog.setTitle(blogDto.getTitle());
        blog.setBody(blogDto.getBody());
        blog.setAuthor(blogDto.getAuthor());
        blog.setCoverImageUrl(blogDto.getCoverImageUrl());
        blog.setTags(blogDto.getTags());

        // Handle Hero relationship
        if (blogDto.getHeroId() != null) {
            Hero hero = heroRepository.findById(blogDto.getHeroId())
                    .orElseThrow(() -> new NotFoundException("Hero by id " + blogDto.getHeroId() + " was not found"));
            blog.setHero(hero);
        } else {
            blog.setHero(null);
        }

        // Handle Villain relationship
        if (blogDto.getVillainId() != null) {
            Villain villain = villainRepository.findById(blogDto.getVillainId())
                    .orElseThrow(() -> new NotFoundException("Villain by id " + blogDto.getVillainId() + " was not found"));
            blog.setVillain(villain);
        } else {
            blog.setVillain(null);
        }

        // Handle AntiHero relationship
        if (blogDto.getAntiHeroId() != null) {
            AntiHero antiHero = antiHeroRepository.findById(blogDto.getAntiHeroId())
                    .orElseThrow(() -> new NotFoundException("AntiHero by id " + blogDto.getAntiHeroId() + " was not found"));
            blog.setAntiHero(antiHero);
        } else {
            blog.setAntiHero(null);
        }
    }
}
