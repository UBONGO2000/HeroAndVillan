package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.CategoryDto;
import com.springbootlearning.learningspringboot.backend.entity.Category;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service pour la gestion des Catégories.
 * 
 * <p>Ce service fournit les opérations CRUD pour les catégories
 * de personnages (Marvel, DC, Anime, etc.).</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Récupère toutes les catégories.
     * 
     * @return un Iterable de toutes les catégories
     */
    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Récupère une catégorie par son identifiant.
     * 
     * @param id l'identifiant de la catégorie
     * @return la catégorie trouvée
     * @throws NotFoundException si la catégorie n'est pas trouvée
     */
    public Category findCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category by id " + id + " was not found")
        );
    }

    /**
     * Récupère une catégorie par son nom.
     * 
     * @param name le nom de la catégorie
     * @return la catégorie trouvée
     * @throws NotFoundException si la catégorie n'est pas trouvée
     */
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Category by name " + name + " was not found")
        );
    }

    /**
     * Supprime une catégorie par son identifiant.
     * 
     * @param id l'identifiant de la catégorie à supprimer
     */
    @Transactional
    public void removeCategoryById(UUID id) {
        categoryRepository.deleteById(id);
        logger.info("Category deleted successfully with id: {}", id);
    }

    /**
     * Crée une nouvelle catégorie.
     * 
     * @param categoryDto les données de la catégorie à créer
     * @return la catégorie créée
     */
    @Transactional
    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImageUrl(categoryDto.getImageUrl());

        Category savedCategory = categoryRepository.save(category);
        logger.info("Category created successfully with id: {}", savedCategory.getId());
        return savedCategory;
    }

    /**
     * Met à jour une catégorie existante.
     * 
     * @param id l'identifiant de la catégorie à mettre à jour
     * @param categoryDto les nouvelles données de la catégorie
     * @return la catégorie mise à jour
     * @throws NotFoundException si la catégorie n'est pas trouvée
     */
    @Transactional
    public Category updateCategory(UUID id, CategoryDto categoryDto) {
        Category category = findCategoryById(id);

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImageUrl(categoryDto.getImageUrl());

        Category updatedCategory = categoryRepository.save(category);
        logger.info("Category updated successfully with id: {}", updatedCategory.getId());
        return updatedCategory;
    }
}