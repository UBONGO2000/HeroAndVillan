package com.springbootlearning.learningspringboot.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) pour la création et la mise à jour des Blogs.
 * 
 * <p>Ce DTO est utilisé pour transférer les données des articles de blog entre
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
    name = "BlogDto",
    description = "DTO représentant un article de blog dans le système Heroes & Villains"
)
public class BlogDto {

    /**
     * Identifiant unique de l'article.
     */
    @Schema(
        name = "id",
        description = "Identifiant unique de l'article (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Titre de l'article de blog.
     */
    @NotBlank(message = "Title is required")
    @Schema(
        name = "title",
        description = "Titre de l'article de blog",
        example = "Top 10 des héros Marvel les plus puissants",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;

    /**
     * Contenu de l'article de blog.
     */
    @NotBlank(message = "Body is required")
    @Schema(
        name = "body",
        description = "Contenu principal de l'article de blog",
        example = "Dans cet article, nous allons explorer les héros les plus puissants de l'univers Marvel...",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String body;

    /**
     * Auteur de l'article de blog.
     */
    @NotBlank(message = "Author is required")
    @Schema(
        name = "author",
        description = "Nom de l'auteur de l'article",
        example = "Georges",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String author;

    /**
     * URL de l'image de couverture de l'article.
     */
    @Schema(
        name = "coverImageUrl",
        description = "URL de l'image de couverture de l'article",
        example = "https://example.com/images/marvel-heroes.jpg"
    )
    private String coverImageUrl;

    /**
     * Tags associés à l'article.
     */
    @Schema(
        name = "tags",
        description = "Tags associés à l'article (séparés par des virgules)",
        example = "Marvel, Héros, Top 10"
    )
    private String tags;

    /**
     * Identifiant du héros associé à l'article.
     */
    @Schema(
        name = "heroId",
        description = "Identifiant du héros associé à l'article (optionnel)",
        example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID heroId;

    /**
     * Nom du héros associé (lecture seule).
     */
    @Schema(
        name = "heroName",
        description = "Nom du héros associé à l'article",
        example = "Spider-Man",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String heroName;

    /**
     * Identifiant du villain associé à l'article.
     */
    @Schema(
        name = "villainId",
        description = "Identifiant du villain associé à l'article (optionnel)",
        example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID villainId;

    /**
     * Nom du villain associé (lecture seule).
     */
    @Schema(
        name = "villainName",
        description = "Nom du villain associé à l'article",
        example = "Lord Voldemort",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String villainName;

    /**
     * Identifiant de l'anti-héros associé à l'article.
     */
    @Schema(
        name = "antiHeroId",
        description = "Identifiant de l'anti-héros associé à l'article (optionnel)",
        example = "550e8400-e29b-41d4-a716-446655440000"
    )
    private UUID antiHeroId;

    /**
     * Nom de l'anti-héros associé (lecture seule).
     */
    @Schema(
        name = "antiHeroName",
        description = "Nom de l'anti-héros associé à l'article",
        example = "Deadpool",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String antiHeroName;

    /**
     * Date de création de l'article.
     */
    @Schema(
        name = "createdAt",
        description = "Date de création de l'article",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;

    /**
     * Date de dernière modification de l'article.
     */
    @Schema(
        name = "updatedAt",
        description = "Date de dernière modification de l'article",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime updatedAt;
}
