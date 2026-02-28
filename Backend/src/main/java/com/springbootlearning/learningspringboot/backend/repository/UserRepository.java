package com.springbootlearning.learningspringboot.backend.repository;

import com.springbootlearning.learningspringboot.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository pour la gestion des entités {@link User}.
 *
 * <p>Fournit les méthodes d'accès aux données pour les utilisateurs du système.</p>
 *
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur
     * @return un Optional contenant l'utilisateur s'il existe
     */
    Optional<User> findByUsername(String username);

    /**
     * Recherche un utilisateur par son email.
     *
     * @param email l'adresse email
     * @return un Optional contenant l'utilisateur s'il existe
     */
    Optional<User> findByEmail(String email);

    /**
     * Vérifie si un nom d'utilisateur existe déjà.
     *
     * @param username le nom d'utilisateur à vérifier
     * @return true si le nom d'utilisateur existe
     */
    boolean existsByUsername(String username);

    /**
     * Vérifie si un email existe déjà.
     *
     * @param email l'email à vérifier
     * @return true si l'email existe
     */
    boolean existsByEmail(String email);
}
