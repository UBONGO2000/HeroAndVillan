package com.springbootlearning.learningspringboot.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entité JPA représentant un commentaire sur un article de blog.
 * 
 * <p>Un commentaire est associé à un article de blog et contient
 * le nom de l'auteur et le contenu du commentaire.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "Comment",
    description = "Entité représentant un commentaire sur un article de blog"
)
public class Comment {

    /**
     * Identifiant unique du commentaire (UUID généré automatiquement).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @Schema(
        name = "id",
        description = "Identifiant unique du commentaire (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Article de blog associé au commentaire.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false)
    @Schema(
        name = "blog",
        description = "Article de blog associé au commentaire",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Blog blog;

    /**
     * Nom de l'auteur du commentaire.
     */
    @NotBlank(message = "Author name is required")
    @Column(nullable = false)
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
    @Column(nullable = false, columnDefinition = "TEXT")
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
    @Column(name = "created_at", updatable = false)
    @Schema(
        name = "createdAt",
        description = "Date de création du commentaire",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;

    /**
     * Méthode appelée avant la persistance de l'entité.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
