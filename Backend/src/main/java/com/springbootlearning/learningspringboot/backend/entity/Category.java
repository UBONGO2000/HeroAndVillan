package com.springbootlearning.learningspringboot.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité JPA représentant une catégorie/univers de personnages.
 * 
 * <p>Les catégories permettent de classifier les héros, villains et anti-héros
 * par univers (Marvel, DC, Anime, etc.) ou par type de média.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "Category",
    description = "Catégorie/Univers de classification des personnages"
)
public class Category {

    /**
     * Identifiant unique de la catégorie (UUID généré automatiquement).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @Schema(
        name = "id",
        description = "Identifiant unique de la catégorie (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Nom de la catégorie (ex: Marvel, DC, Anime).
     */
    @NotBlank(message = "Category name is required")
    @Column(nullable = false, unique = true)
    @Schema(
        name = "name",
        description = "Nom de la catégorie/univers",
        example = "Marvel",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    /**
     * Description de la catégorie.
     */
    @Schema(
        name = "description",
        description = "Description de la catégorie",
        example = "Univers Marvel Comics incluant les Avengers, X-Men, etc."
    )
    private String description;

    /**
     * URL de l'image/logo de la catégorie.
     */
    @Schema(
        name = "imageUrl",
        description = "URL de l'image ou logo représentant la catégorie",
        example = "https://example.com/images/marvel-logo.png"
    )
    private String imageUrl;

    /**
     * Date de création de l'entité.
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * Date de dernière modification de l'entité.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Méthode appelée avant la persistance de l'entité.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Méthode appelée avant la mise à jour de l'entité.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
