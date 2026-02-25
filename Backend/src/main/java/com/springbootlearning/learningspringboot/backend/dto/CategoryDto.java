package com.springbootlearning.learningspringboot.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO (Data Transfer Object) pour l'entité Category.
 * 
 * <p>Utilisé pour transférer les données de catégorie entre les couches
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
    name = "CategoryDto",
    description = "DTO représentant une catégorie/univers de personnages"
)
public class CategoryDto {

    /**
     * Identifiant unique de la catégorie.
     */
    @Schema(
        name = "id",
        description = "Identifiant unique de la catégorie (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Nom de la catégorie.
     */
    @NotBlank(message = "Category name is required")
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
}
