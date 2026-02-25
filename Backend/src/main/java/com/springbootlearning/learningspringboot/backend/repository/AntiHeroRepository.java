package com.springbootlearning.learningspringboot.backend.repository;

import com.springbootlearning.learningspringboot.backend.entity.AntiHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository JPA pour l'entité AntiHero.
 * 
 * <p>Fournit les opérations CRUD de base pour les anti-héros.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface AntiHeroRepository extends JpaRepository<AntiHero, UUID> {

    /**
     * Trouve tous les anti-héros d'une catégorie donnée.
     * 
     * @param categoryId l'identifiant de la catégorie
     * @return la liste des anti-héros de cette catégorie
     */
    List<AntiHero> findByCategoryId(UUID categoryId);

    /**
     * Trouve un anti-héros par son nom d'anti-héros.
     * 
     * @param antiHeroName le nom d'anti-héros
     * @return un Optional contenant l'anti-héros si trouvé
     */
    Optional<AntiHero> findByAntiHeroName(String antiHeroName);

    /**
     * Trouve tous les anti-héros dont le nom contient la chaîne donnée.
     * 
     * @param antiHeroName la chaîne à rechercher dans le nom d'anti-héros
     * @return la liste des anti-héros correspondants
     */
    List<AntiHero> findByAntiHeroNameContainingIgnoreCase(String antiHeroName);

    /**
     * Trouve tous les anti-héros par affiliation.
     * 
     * @param affiliation l'affiliation recherchée
     * @return la liste des anti-héros de cette affiliation
     */
    List<AntiHero> findByAffiliationContainingIgnoreCase(String affiliation);

    /**
     * Vérifie si un anti-héros existe par son nom d'anti-héros.
     * 
     * @param antiHeroName le nom d'anti-héros
     * @return true si l'anti-héros existe, false sinon
     */
    boolean existsByAntiHeroName(String antiHeroName);
}
