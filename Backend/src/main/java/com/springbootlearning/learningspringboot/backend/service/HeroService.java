package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.HeroDto;
import com.springbootlearning.learningspringboot.backend.entity.Category;
import com.springbootlearning.learningspringboot.backend.entity.Hero;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.mapper.HeroMapper;
import com.springbootlearning.learningspringboot.backend.repository.CategoryRepository;
import com.springbootlearning.learningspringboot.backend.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
@CacheConfig(cacheNames = "heroes")
public class HeroService {

    private static final Logger logger = LoggerFactory.getLogger(HeroService.class);
    private final HeroRepository heroRepository;
    private final CategoryRepository categoryRepository;
    private final HeroMapper heroMapper;

    public HeroService(HeroRepository heroRepository, CategoryRepository categoryRepository, HeroMapper heroMapper) {
        this.heroRepository = heroRepository;
        this.categoryRepository = categoryRepository;
        this.heroMapper = heroMapper;
    }

    /**
     * Récupère tous les héros avec pagination.
     * 
     * @param pageable les informations de pagination
     * @return une page de DTOs de héros
     */
    @Cacheable(key = "'page_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<HeroDto> findAllHeroes(Pageable pageable) {
        logger.info("Fetching heroes page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<Hero> heroes = heroRepository.findAll(pageable);
        return heroes.map(heroMapper::toDto);
    }

    /**
     * Récupère tous les héros sans pagination (pour compatibilité).
     * 
     * @return une liste de DTOs de tous les héros
     */
    @Cacheable(key = "'all'")
    public List<HeroDto> findAllHeroes() {
        logger.info("Fetching all heroes");
        List<Hero> heroes = heroRepository.findAll();
        return heroMapper.toDtoList(heroes);
    }

    /**
     * Récupère un héros par son identifiant.
     * 
     * @param id l'identifiant du héros
     * @return le DTO du héros trouvé
     * @throws NotFoundException si le héros n'est pas trouvé
     */
    @Cacheable(key = "#id")
    public HeroDto findHeroById(UUID id) {
        logger.info("Fetching hero with id: {}", id);
        Hero hero = heroRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Hero by id " + id + " was not found")
        );
        return heroMapper.toDto(hero);
    }

    /**
     * Supprime un héros par son identifiant.
     * 
     * @param id l'identifiant du héros à supprimer
     */
    @CacheEvict(key = "#id")
    @Transactional
    public void removeHeroById(UUID id) {
        logger.info("Deleting hero with id: {}", id);
        heroRepository.deleteById(id);
        logger.info("Hero deleted successfully with id: {}", id);
    }

    /**
     * Crée un nouveau héros.
     * 
     * @param heroDto les données du héros à créer
     * @return le DTO du héros créé
     * @throws NotFoundException si la catégorie spécifiée n'existe pas
     */
    @CachePut(key = "#result.id")
    @CacheEvict(key = "'all'", allEntries = true)
    @Transactional
    public HeroDto createHero(HeroDto heroDto) {
        logger.info("Creating new hero: {}", heroDto.getHeroName());
        Hero hero = heroMapper.toEntity(heroDto);
        
        // Set category if provided
        if (heroDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(heroDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found with id: " + heroDto.getCategoryId()));
            hero.setCategory(category);
        }
        
        Hero savedHero = heroRepository.save(hero);
        logger.info("Hero created successfully with id: {}", savedHero.getId());
        return heroMapper.toDto(savedHero);
    }

    /**
     * Met à jour un héros existant.
     * 
     * @param id l'identifiant du héros à mettre à jour
     * @param heroDto les nouvelles données du héros
     * @return le DTO du héros mis à jour
     * @throws NotFoundException si le héros ou la catégorie n'est pas trouvé
     */
    @CachePut(key = "#id")
    @CacheEvict(key = "'all'", allEntries = true)
    @Transactional
    public HeroDto updateHero(UUID id, HeroDto heroDto) {
        logger.info("Updating hero with id: {}", id);
        Hero hero = heroRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Hero by id " + id + " was not found")
        );

        heroMapper.updateEntityFromDto(heroDto, hero);
        
        // Update category if provided
        if (heroDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(heroDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found with id: " + heroDto.getCategoryId()));
            hero.setCategory(category);
        }

        Hero updatedHero = heroRepository.save(hero);
        logger.info("Hero updated successfully with id: {}", updatedHero.getId());
        return heroMapper.toDto(updatedHero);
    }

    /**
     * Recherche des héros par catégorie.
     * 
     * @param categoryId l'identifiant de la catégorie
     * @return la liste des DTOs de héros de cette catégorie
     */
    public List<HeroDto> findHeroesByCategory(UUID categoryId) {
        logger.info("Fetching heroes by category id: {}", categoryId);
        List<Hero> heroes = heroRepository.findByCategoryId(categoryId);
        return heroMapper.toDtoList(heroes);
    }
}
