package com.springbootlearning.learningspringboot.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Entité JPA représentant un utilisateur dans le système.
 *
 * <p>Cette entité gère l'authentification et l'autorisation des utilisateurs.
 * Elle implémente les fonctionnalités de sécurité Spring Security.</p>
 *
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "User",
    description = "Entité représentant un utilisateur du système"
)
public class User {

    /**
     * Identifiant unique de l'utilisateur (UUID généré automatiquement).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @Schema(
        name = "id",
        description = "Identifiant unique de l'utilisateur (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Nom d'utilisateur unique.
     */
    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    @Schema(
        name = "username",
        description = "Nom d'utilisateur unique",
        example = "johndoe",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String username;

    /**
     * Adresse email unique.
     */
    @NotBlank
    @Size(max = 100)
    @Email
    @Column(nullable = false, length = 100)
    @Schema(
        name = "email",
        description = "Adresse email de l'utilisateur",
        example = "john.doe@example.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    /**
     * Mot de passe hashé.
     */
    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    @Schema(
        name = "password",
        description = "Mot de passe (sera hashé)",
        example = "********",
        requiredMode = Schema.RequiredMode.REQUIRED,
        accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String password;

    /**
     * Indique si le compte est actif.
     */
    @Column(nullable = false)
    @Schema(
        name = "active",
        description = "Indique si le compte est actif",
        example = "true"
    )
    private boolean active = true;

    /**
     * Date de création du compte.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(
        name = "createdAt",
        description = "Date de création du compte",
        example = "2024-01-15T10:30:00"
    )
    private LocalDateTime createdAt;

    /**
     * Date de dernière mise à jour.
     */
    @Column(name = "updated_at")
    @Schema(
        name = "updatedAt",
        description = "Date de dernière mise à jour",
        example = "2024-01-20T14:45:00"
    )
    private LocalDateTime updatedAt;

    /**
     * Rôles associés à l'utilisateur.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Schema(
        name = "roles",
        description = "Rôles de l'utilisateur"
    )
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
