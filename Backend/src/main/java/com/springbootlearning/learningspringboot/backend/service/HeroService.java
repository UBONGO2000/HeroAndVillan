package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.HeroDto;
import com.springbootlearning.learningspringboot.backend.entity.Category;
import com.springbootlearning.learningspringboot.backend.entity.Hero;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.repository.CategoryRepository;
import com.springbootlearning.learningspringboot.backend.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service pour la gestion des Héros.
 * 
 * <p>Ce service fournit les opérations CRUD pour les héros,
 * incluant la gestion des relations avec les catégories.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class HeroService {

    private static final Logger logger = LoggerFactory.getLogger(HeroService.class);
    private final HeroRepository heroRepository;
    private final CategoryRepository categoryRepository;

    public HeroService(HeroRepository heroRepository, CategoryRepository categoryRepository) {
        this.heroRepository = heroRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Récupère tous les héros.
     * 
     * @return un Iterable de tous les héros
     */
    public Iterable<Hero> findAllHeroes() {
        return heroRepository.findAll();
    }

    /**
     * Récupère un héros par son identifiant.
     * 
     * @param id l'identifiant du héros
     * @return le héros trouvé
     * @throws NotFoundException si le héros n'est pas trouvé
     */
    public Hero findHeroById(UUID id) {
        return heroRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Hero by id " + id + " was not found")
        );
    }

    /**
     * Supprime un héros par son identifiant.
     * 
     * @param id l'identifiant du héros à supprimer
     */
    @Transactional
    public void removeHeroById(UUID id) {
        heroRepository.deleteById(id);
        logger.info("Hero deleted successfully with id: {}", id);
    }

    /**
     * Crée un nouveau héros.
     * 
     * @param heroDto les données du héros à créer
     * @return le héros créé
     * @throws NotFoundException si la catégorie spécifiée n'existe pas
     */
    @Transactional
    public Hero createHero(HeroDto heroDto) {
        Hero hero = new Hero();

        updateHeroFromDto(hero, heroDto);

        Hero savedHero = heroRepository.save(hero);
        logger.info("Hero created successfully with id: {}", savedHero.getId());
        return savedHero;
    }

    /**
     * Met à jour un héros existant.
     * 
     * @param id l'identifiant du héros à mettre à jour
     * @param heroDto les nouvelles données du héros
     * @return le héros mis à jour
     * @throws NotFoundException si le héros ou la catégorie n'est pas trouvé
     */
    @Transactional
    public Hero updateHero(UUID id, HeroDto heroDto) {
        Hero hero = findHeroById(id);

        updateHeroFromDto(hero, heroDto);

        Hero updatedHero = heroRepository.save(hero);
        logger.info("Hero updated successfully with id: {}", updatedHero.getId());
        return updatedHero;
    }

    /**
     * Met à jour les propriétés d'un héros à partir du DTO.
     * 
     * @param hero le héros à mettre à jour
     * @param heroDto les données source
     */
    private void updateHeroFromDto(Hero hero, HeroDto heroDto) {
        hero.setFirstName(heroDto.getFirstName());
        hero.setLastName(heroDto.getLastName());
        hero.setHeroName(heroDto.getHeroName());
        hero.setPower(heroDto.getPower());
        hero.setAffiliation(heroDto.getAffiliation());
        hero.setRating(heroDto.getRating());
        hero.setImageUrl(heroDto.getImageUrl());
        hero.setBiography(heroDto.getBiography());

        if (heroDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(heroDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category by id " + heroDto.getCategoryId() + " was not found"));
            hero.setCategory(category);
        }
    }
}
