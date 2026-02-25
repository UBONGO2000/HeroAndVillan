package com.springbootlearning.learningspringboot.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO (Data Transfer Object) pour l'entité Hero.
 * 
 * <p>Utilisé pour transférer les données de héros entre les couches
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
    name = "HeroDto",
    description = "DTO représentant un héros dans le système Heroes & Villains"
)
public class HeroDto {

    /**
     * Identifiant unique du héros.
     */
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
     * Identifiant de la catégorie/univers du héros.
     */
    @Schema(
        name = "categoryId",
        description = "Identifiant de la catégorie/univers du héros",
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
     * Note moyenne du héros (0.0 à 5.0).
     */
    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
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
    private String biography;
}
