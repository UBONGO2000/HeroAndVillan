package com.springbootlearning.learningspringboot.backend.repository;

import com.springbootlearning.learningspringboot.backend.entity.Villain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository JPA pour l'entité Villain.
 * 
 * <p>Fournit les opérations CRUD de base pour les villains.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface VillainRepository extends JpaRepository<Villain, UUID> {

    /**
     * Trouve tous les villains d'une catégorie donnée.
     * 
     * @param categoryId l'identifiant de la catégorie
     * @return la liste des villains de cette catégorie
     */
    List<Villain> findByCategoryId(UUID categoryId);

    /**
     * Trouve un villain par son nom de villain.
     * 
     * @param villainName le nom de villain
     * @return un Optional contenant le villain si trouvé
     */
    Optional<Villain> findByVillainName(String villainName);

    /**
     * Trouve tous les villains dont le nom contient la chaîne donnée.
     * 
     * @param villainName la chaîne à rechercher dans le nom de villain
     * @return la liste des villains correspondants
     */
    List<Villain> findByVillainNameContainingIgnoreCase(String villainName);

    /**
     * Trouve tous les villains par affiliation.
     * 
     * @param affiliation l'affiliation recherchée
     * @return la liste des villains de cette affiliation
     */
    List<Villain> findByAffiliationContainingIgnoreCase(String affiliation);

    /**
     * Vérifie si un villain existe par son nom de villain.
     * 
     * @param villainName le nom de villain
     * @return true si le villain existe, false sinon
     */
    boolean existsByVillainName(String villainName);
}
