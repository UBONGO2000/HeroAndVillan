package com.springbootlearning.learningspringboot.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité JPA représentant un Anti-Héros dans le système.
 * 
 * <p>Un anti-héros est un personnage qui se situe entre le héros et le villain,
 * ayant souvent des motivations moralement grises.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "anti_hero")
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "AntiHero",
    description = "Entité représentant un anti-héros dans le système Heroes & Villains"
)
public class AntiHero {

    /**
     * Identifiant unique de l'anti-héros (UUID généré automatiquement).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @Schema(
        name = "id",
        description = "Identifiant unique de l'anti-héros (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Prénom de l'anti-héros.
     */
    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    @Schema(
        name = "firstName",
        description = "Prénom de l'anti-héros",
        example = "Wade",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String firstName;

    /**
     * Nom de famille de l'anti-héros.
     */
    @Schema(
        name = "lastName",
        description = "Nom de famille de l'anti-héros",
        example = "Wilson"
    )
    @Column(nullable = true)
    private String lastName;

    /**
     * Nom de super-héros/anti-héros (identité secrète).
     */
    @Schema(
        name = "antiHeroName",
        description = "Nom de l'anti-héros (identité secrète)",
        example = "Deadpool"
    )
    private String antiHeroName;

    /**
     * Maison à laquelle appartient l'anti-héros.
     */
    @Schema(
        name = "house",
        description = "Maison ou affiliation de l'anti-héros",
        example = "X-Force"
    )
    private String house;

    /**
     * Surnom ou alias de l'anti-héros.
     */
    @Schema(
        name = "knownAs",
        description = "Surnom ou alias par lequel l'anti-héros est connu",
        example = "Merc with a Mouth"
    )
    private String knownAs;

    /**
     * Pouvoir(s) de l'anti-héros.
     */
    @Schema(
        name = "power",
        description = "Pouvoir(s) de l'anti-héros",
        example = "Régénération, immortalité, maîtrise des armes"
    )
    private String power;

    /**
     * Affiliation de l'anti-héros (équipe).
     */
    @Schema(
        name = "affiliation",
        description = "Affiliation ou équipe de l'anti-héros",
        example = "X-Force, Avengers (parfois)"
    )
    private String affiliation;

    /**
     * Catégorie/Univers de l'anti-héros.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Schema(
        name = "category",
        description = "Catégorie/Univers de l'anti-héros"
    )
    private Category category;

    /**
     * Note moyenne de l'anti-héros (0.0 à 5.0).
     */
    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    @Column(precision = 2, scale = 1)
    @Schema(
        name = "rating",
        description = "Note moyenne de l'anti-héros (0.0 à 5.0)",
        example = "4.7",
        minimum = "0.0",
        maximum = "5.0"
    )
    private BigDecimal rating;

    /**
     * URL de l'image de l'anti-héros.
     */
    @Schema(
        name = "imageUrl",
        description = "URL de l'image de l'anti-héros",
        example = "https://example.com/images/deadpool.jpg"
    )
    private String imageUrl;

    /**
     * Biographie de l'anti-héros.
     */
    @Schema(
        name = "biography",
        description = "Biographie ou histoire de l'anti-héros",
        example = "Wade Wilson, ancien mercenaire, est devenu Deadpool après..."
    )
    @Column(columnDefinition = "TEXT")
    private String biography;

    /**
     * Date de création de l'entité.
     */
    @Column(name = "created_at", updatable = false)
    @Schema(
        name = "createdAt",
        description = "Date de création de l'entité",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;

    /**
     * Date de dernière modification de l'entité.
     */
    @Column(name = "updated_at")
    @Schema(
        name = "updatedAt",
        description = "Date de dernière modification de l'entité",
        accessMode = Schema.AccessMode.READ_ONLY
    )
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
