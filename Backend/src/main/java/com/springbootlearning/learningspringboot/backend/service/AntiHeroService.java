package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.AntiHeroDto;
import com.springbootlearning.learningspringboot.backend.entity.AntiHero;
import com.springbootlearning.learningspringboot.backend.entity.Category;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.repository.AntiHeroRepository;
import com.springbootlearning.learningspringboot.backend.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service pour la gestion des Anti-Héros.
 * 
 * <p>Ce service fournit les opérations CRUD pour les anti-héros,
 * incluant la gestion des relations avec les catégories.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class AntiHeroService {

    private static final Logger logger = LoggerFactory.getLogger(AntiHeroService.class);
    private final AntiHeroRepository antiHeroRepository;
    private final CategoryRepository categoryRepository;

    public AntiHeroService(AntiHeroRepository antiHeroRepository, CategoryRepository categoryRepository) {
        this.antiHeroRepository = antiHeroRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Récupère tous les anti-héros.
     * 
     * @return un Iterable de tous les anti-héros
     */
    public Iterable<AntiHero> findAllAntiHeroes() {
        return antiHeroRepository.findAll();
    }

    /**
     * Récupère un anti-héros par son identifiant.
     * 
     * @param id l'identifiant de l'anti-héros
     * @return l'anti-héros trouvé
     * @throws NotFoundException si l'anti-héros n'est pas trouvé
     */
    public AntiHero findAntiHeroById(UUID id) {
        return antiHeroRepository.findById(id).orElseThrow(
                () -> new NotFoundException("AntiHero by id " + id + " was not found")
        );
    }

    /**
     * Supprime un anti-héros par son identifiant.
     * 
     * @param id l'identifiant de l'anti-héros à supprimer
     */
    @Transactional
    public void removeAntiHeroById(UUID id) {
        antiHeroRepository.deleteById(id);
        logger.info("AntiHero deleted successfully with id: {}", id);
    }

    /**
     * Crée un nouvel anti-héros.
     * 
     * @param antiHeroDto les données de l'anti-héros à créer
     * @return l'anti-héros créé
     * @throws NotFoundException si la catégorie spécifiée n'existe pas
     */
    @Transactional
    public AntiHero createAntiHero(AntiHeroDto antiHeroDto) {
        AntiHero antiHero = new AntiHero();

        updateAntiHeroFromDto(antiHero, antiHeroDto);

        AntiHero savedAntiHero = antiHeroRepository.save(antiHero);
        logger.info("AntiHero created successfully with id: {}", savedAntiHero.getId());
        return savedAntiHero;
    }

    /**
     * Met à jour un anti-héros existant.
     * 
     * @param id l'identifiant de l'anti-héros à mettre à jour
     * @param antiHeroDto les nouvelles données de l'anti-héros
     * @return l'anti-héros mis à jour
     * @throws NotFoundException si l'anti-héros ou la catégorie n'est pas trouvé
     */
    @Transactional
    public AntiHero updateAntiHero(UUID id, AntiHeroDto antiHeroDto) {
        AntiHero antiHero = findAntiHeroById(id);

        updateAntiHeroFromDto(antiHero, antiHeroDto);

        AntiHero updatedAntiHero = antiHeroRepository.save(antiHero);
        logger.info("AntiHero updated successfully with id: {}", updatedAntiHero.getId());
        return updatedAntiHero;
    }

    /**
     * Met à jour les propriétés d'un anti-héros à partir du DTO.
     * 
     * @param antiHero l'anti-héros à mettre à jour
     * @param antiHeroDto les données source
     */
    private void updateAntiHeroFromDto(AntiHero antiHero, AntiHeroDto antiHeroDto) {
        antiHero.setFirstName(antiHeroDto.getFirstName());
        antiHero.setLastName(antiHeroDto.getLastName());
        antiHero.setAntiHeroName(antiHeroDto.getAntiHeroName());
        antiHero.setHouse(antiHeroDto.getHouse());
        antiHero.setKnownAs(antiHeroDto.getKnownAs());
        antiHero.setPower(antiHeroDto.getPower());
        antiHero.setAffiliation(antiHeroDto.getAffiliation());
        antiHero.setRating(antiHeroDto.getRating());
        antiHero.setImageUrl(antiHeroDto.getImageUrl());
        antiHero.setBiography(antiHeroDto.getBiography());

        if (antiHeroDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(antiHeroDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category by id " + antiHeroDto.getCategoryId() + " was not found"));
            antiHero.setCategory(category);
        }
    }
}
