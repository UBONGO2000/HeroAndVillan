package com.springbootlearning.learningspringboot.backend.repository;

import com.springbootlearning.learningspringboot.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository JPA pour l'entité Category.
 * 
 * <p>Fournit les opérations CRUD de base pour les catégories de personnages.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    /**
     * Trouve une catégorie par son nom.
     * 
     * @param name le nom de la catégorie
     * @return un Optional contenant la catégorie si trouvée
     */
    Optional<Category> findByName(String name);

    /**
     * Vérifie si une catégorie existe par son nom.
     * 
     * @param name le nom de la catégorie
     * @return true si la catégorie existe, false sinon
     */
    boolean existsByName(String name);
}
