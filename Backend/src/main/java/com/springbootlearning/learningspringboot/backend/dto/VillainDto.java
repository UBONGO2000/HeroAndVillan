package com.springbootlearning.learningspringboot.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) pour la création et la mise à jour des Villains.
 * 
 * <p>Ce DTO est utilisé pour transférer les données des villains entre
 * les couches de l'application sans exposer directement l'entité JPA.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "VillainDto",
    description = "DTO représentant un villain dans le système Heroes & Villains"
)
public class VillainDto {

    /**
     * Identifiant unique du villain.
     */
    @Schema(
        name = "id",
        description = "Identifiant unique du villain (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Prénom du villain.
     */
    @NotBlank(message = "First name is required")
    @Schema(
        name = "firstName",
        description = "Prénom du villain",
        example = "Tom",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String firstName;

    /**
     * Nom de famille du villain.
     */
    @NotBlank(message = "Last name is required")
    @Schema(
        name = "lastName",
        description = "Nom de famille du villain",
        example = "Riddle",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String lastName;

    /**
     * Nom de super-villain (identité secrète).
     */
    @Schema(
        name = "villainName",
        description = "Nom de super-villain (identité secrète)",
        example = "Lord Voldemort"
    )
    private String villainName;

    /**
     * Maison à laquelle appartient le villain.
     */
    @Schema(
        name = "house",
        description = "Maison ou affiliation du villain",
        example = "Slytherin"
    )
    private String house;

    /**
     * Surnom ou alias du villain.
     */
    @Schema(
        name = "knownAs",
        description = "Surnom ou alias par lequel le villain est connu",
        example = "He-Who-Must-Not-Be-Named"
    )
    private String knownAs;

    /**
     * Pouvoir(s) du villain.
     */
    @Schema(
        name = "power",
        description = "Pouvoir(s) du villain",
        example = "Magie noire, Legilimencie, Immortalité"
    )
    private String power;

    /**
     * Affiliation du villain (organisation).
     */
    @Schema(
        name = "affiliation",
        description = "Affiliation ou organisation du villain",
        example = "Death Eaters"
    )
    private String affiliation;

    /**
     * Identifiant de la catégorie/univers du villain.
     */
    @Schema(
        name = "categoryId",
        description = "Identifiant de la catégorie/univers du villain",
        example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID categoryId;

    /**
     * Nom de la catégorie (lecture seule).
     */
    @Schema(
        name = "categoryName",
        description = "Nom de la catégorie/univers",
        example = "Harry Potter",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String categoryName;

    /**
     * Note moyenne du villain (0.0 à 5.0).
     */
    @DecimalMin(value = "0.0", message = "Rating must be at least 0.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    @Schema(
        name = "rating",
        description = "Note moyenne du villain (0.0 à 5.0)",
        example = "4.8",
        minimum = "0.0",
        maximum = "5.0"
    )
    private BigDecimal rating;

    /**
     * URL de l'image du villain.
     */
    @Schema(
        name = "imageUrl",
        description = "URL de l'image du villain",
        example = "https://example.com/images/voldemort.jpg"
    )
    private String imageUrl;

    /**
     * Biographie du villain.
     */
    @Schema(
        name = "biography",
        description = "Biographie ou histoire du villain",
        example = "Tom Elvis Jedusor, plus connu sous le nom de Lord Voldemort..."
    )
    private String biography;
}
