package com.springbootlearning.learningspringboot.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité JPA représentant un Héros dans le système.
 * 
 * <p>Un héros est un personnage positif caractérisé par son nom, ses pouvoirs,
 * son univers et son affiliation.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "hero")
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "Hero",
    description = "Entité représentant un héros dans le système Heroes & Villains"
)
public class Hero {

    /**
     * Identifiant unique du héros (UUID généré automatiquement).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @Schema(
        name = "id",
        description = "Identifiant unique du héros (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Prénom du héros.
     */
    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    @Schema(
        name = "firstName",
        description = "Prénom du héros",
        example = "Peter",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String firstName;

    /**
     * Nom de famille du héros.
     */
    @NotBlank(message = "Last name is required")
    @Column(nullable = false)
    @Schema(
        name = "lastName",
        description = "Nom de famille du héros",
        example = "Parker",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String lastName;

    /**
     * Nom de super-héros (identité secrète).
     */
    @Schema(
        name = "heroName",
        description = "Nom de super-héros (identité secrète)",
        example = "Spider-Man"
    )
    private String heroName;

    /**
     * Pouvoir(s) du héros.
     */
    @Schema(
        name = "power",
        description = "Pouvoir(s) du héros",
        example = "Super force, agilité, sens araignée, lance-toiles"
    )
    private String power;

    /**
     * Affiliation du héros (équipe).
     */
    @Schema(
        name = "affiliation",
        description = "Affiliation ou équipe du héros",
        example = "Avengers"
    )
    private String affiliation;

    /**
     * Catégorie/Univers du héros.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Schema(
        name = "category",
        description = "Catégorie/Univers du héros"
    )
    private Category category;

    /**
     * Note moyenne du héros (0.0 à 5.0).
     */
    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    @Column(precision = 2, scale = 1)
    @Schema(
        name = "rating",
        description = "Note moyenne du héros (0.0 à 5.0)",
        example = "4.5",
        minimum = "0.0",
        maximum = "5.0"
    )
    private BigDecimal rating;

    /**
     * URL de l'image du héros.
     */
    @Schema(
        name = "imageUrl",
        description = "URL de l'image du héros",
        example = "https://example.com/images/spiderman.jpg"
    )
    private String imageUrl;

    /**
     * Biographie du héros.
     */
    @Schema(
        name = "biography",
        description = "Biographie ou histoire du héros",
        example = "Mordu par une araignée radioactive, Peter Parker a acquis des pouvoirs extraordinaires..."
    )
    @Column(columnDefinition = "TEXT")
    private String biography;

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
