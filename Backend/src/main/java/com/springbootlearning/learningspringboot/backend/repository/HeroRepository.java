package com.springbootlearning.learningspringboot.backend.repository;

import com.springbootlearning.learningspringboot.backend.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository JPA pour l'entité Hero.
 * 
 * <p>Fournit les opérations CRUD de base pour les héros.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface HeroRepository extends JpaRepository<Hero, UUID> {

    /**
     * Trouve tous les héros d'une catégorie donnée.
     * 
     * @param categoryId l'identifiant de la catégorie
     * @return la liste des héros de cette catégorie
     */
    List<Hero> findByCategoryId(UUID categoryId);

    /**
     * Trouve un héros par son nom de héros.
     * 
     * @param heroName le nom de héros
     * @return un Optional contenant le héros si trouvé
     */
    Optional<Hero> findByHeroName(String heroName);

    /**
     * Trouve tous les héros dont le nom contient la chaîne donnée.
     * 
     * @param heroName la chaîne à rechercher dans le nom de héros
     * @return la liste des héros correspondants
     */
    List<Hero> findByHeroNameContainingIgnoreCase(String heroName);

    /**
     * Trouve tous les héros par affiliation.
     * 
     * @param affiliation l'affiliation recherchée
     * @return la liste des héros de cette affiliation
     */
    List<Hero> findByAffiliationContainingIgnoreCase(String affiliation);

    /**
     * Vérifie si un héros existe par son nom de héros.
     * 
     * @param heroName le nom de héros
     * @return true si le héros existe, false sinon
     */
    boolean existsByHeroName(String heroName);
}
