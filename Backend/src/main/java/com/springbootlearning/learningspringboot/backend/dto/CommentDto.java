package com.springbootlearning.learningspringboot.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO (Data Transfer Object) pour l'entité Comment.
 * 
 * <p>Utilisé pour transférer les données de commentaire entre les couches
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
    name = "CommentDto",
    description = "DTO représentant un commentaire sur un article de blog"
)
public class CommentDto {

    /**
     * Identifiant unique du commentaire.
     */
    @Schema(
        name = "id",
        description = "Identifiant unique du commentaire (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Identifiant de l'article de blog associé.
     */
    @NotNull(message = "Blog ID is required")
    @Schema(
        name = "blogId",
        description = "Identifiant de l'article de blog associé",
        example = "550e8400-e29b-41d4-a716-446655440000",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private UUID blogId;

    /**
     * Nom de l'auteur du commentaire.
     */
    @NotBlank(message = "Author name is required")
    @Schema(
        name = "authorName",
        description = "Nom de l'auteur du commentaire",
        example = "Jean Dupont",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String authorName;

    /**
     * Contenu du commentaire.
     */
    @NotBlank(message = "Comment content is required")
    @Schema(
        name = "content",
        description = "Contenu du commentaire",
        example = "Super article ! J'ai beaucoup appris sur les héros Marvel.",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String content;

    /**
     * Date de création du commentaire.
     */
    @Schema(
        name = "createdAt",
        description = "Date de création du commentaire",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;
}
