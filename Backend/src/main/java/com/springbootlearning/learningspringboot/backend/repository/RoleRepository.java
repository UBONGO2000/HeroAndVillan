package com.springbootlearning.learningspringboot.backend.repository;

import com.springbootlearning.learningspringboot.backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository pour la gestion des entités {@link Role}.
 *
 * <p>Fournit les méthodes d'accès aux données pour les rôles utilisateurs.</p>
 *
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    /**
     * Recherche un rôle par son nom.
     *
     * @param name le nom du rôle
     * @return un Optional contenant le rôle s'il existe
     */
    Optional<Role> findByName(String name);

    /**
     * Vérifie si un rôle existe par son nom.
     *
     * @param name le nom du rôle
     * @return true si le rôle existe
     */
    boolean existsByName(String name);
}
