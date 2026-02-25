package com.springbootlearning.learningspringboot.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
 * DTO (Data Transfer Object) pour l'entité AntiHero.
 * 
 * <p>Utilisé pour transférer les données d'anti-héros entre les couches
 * de l'application sans exposer directement l'entité JPA.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "AntiHeroDto",
    description = "DTO représentant un anti-héros dans le système Heroes & Villains"
)
public class AntiHeroDto {

    /**
     * Identifiant unique de l'anti-héros.
     */
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
    @NotBlank(message = "Last name is required")
    @Schema(
        name = "lastName",
        description = "Nom de famille de l'anti-héros",
        example = "Wilson",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String lastName;

    /**
     * Nom de l'anti-héros (identité secrète).
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
     * Identifiant de la catégorie/univers de l'anti-héros.
     */
    @Schema(
        name = "categoryId",
        description = "Identifiant de la catégorie/univers de l'anti-héros",
        example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID categoryId;

    /**
     * Nom de la catégorie (lecture seule).
     */
    @Schema(
        name = "categoryName",
        description = "Nom de la catégorie/univers",
        example = "Marvel",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String categoryName;

    /**
     * Note moyenne de l'anti-héros (0.0 à 5.0).
     */
    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
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
    private String biography;

    /**
     * Date de création de l'entité.
     */
    @Schema(
        name = "createdAt",
        description = "Date de création de l'entité",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;
}
