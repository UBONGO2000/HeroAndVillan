package com.springbootlearning.learningspringboot.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entité JPA représentant un article de Blog dans le système.
 * 
 * <p>Un blog est un article contenant un titre, un contenu, un auteur,
 * et peut être associé à des héros, villains ou anti-héros.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "blog")
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    name = "Blog",
    description = "Entité représentant un article de blog dans le système Heroes & Villains"
)
public class Blog {
    
    /**
     * Identifiant unique du blog (UUID généré automatiquement).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @Schema(
        name = "id",
        description = "Identifiant unique du blog (UUID)",
        example = "550e8400-e29b-41d4-a716-446655440000",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private UUID id;

    /**
     * Titre de l'article de blog.
     */
    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    @Schema(
        name = "title",
        description = "Titre de l'article de blog",
        example = "Top 10 des héros Marvel les plus puissants",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;

    /**
     * Contenu principal de l'article de blog.
     */
    @NotBlank(message = "Body is required")
    @Column(nullable = false, columnDefinition = "TEXT")
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
    @Column(nullable = false)
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
     * Héros associé à l'article (optionnel).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_id")
    @Schema(
        name = "hero",
        description = "Héros associé à l'article (optionnel)"
    )
    private Hero hero;

    /**
     * Villain associé à l'article (optionnel).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "villain_id")
    @Schema(
        name = "villain",
        description = "Villain associé à l'article (optionnel)"
    )
    private Villain villain;

    /**
     * Anti-héros associé à l'article (optionnel).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anti_hero_id")
    @Schema(
        name = "antiHero",
        description = "Anti-héros associé à l'article (optionnel)"
    )
    private AntiHero antiHero;

    /**
     * Liste des commentaires sur l'article.
     */
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(
        name = "comments",
        description = "Liste des commentaires sur l'article",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private List<Comment> comments = new ArrayList<>();

    /**
     * Date de création de l'article.
     */
    @Column(name = "created_at", updatable = false)
    @Schema(
        name = "createdAt",
        description = "Date de création de l'article",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;

    /**
     * Date de dernière modification de l'article.
     */
    @Column(name = "updated_at")
    @Schema(
        name = "updatedAt",
        description = "Date de dernière modification de l'article",
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
