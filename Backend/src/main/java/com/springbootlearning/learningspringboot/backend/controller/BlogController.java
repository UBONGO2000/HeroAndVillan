package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.BlogDto;
import com.springbootlearning.learningspringboot.backend.entity.Blog;
import com.springbootlearning.learningspringboot.backend.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des Blogs.
 * 
 * <p>Ce contrôleur expose les endpoints CRUD pour la gestion des articles de blog
 * sur les héros, vilains et anti-héros.</p>
 * 
 * <h2>Endpoints disponibles:</h2>
 * <ul>
 *   <li>GET /api/v1/blogs - Récupérer tous les blogs</li>
 *   <li>GET /api/v1/blogs/{id} - Récupérer un blog par ID</li>
 *   <li>POST /api/v1/blogs - Créer un nouveau blog</li>
 *   <li>PUT /api/v1/blogs/{id} - Mettre à jour un blog</li>
 *   <li>DELETE /api/v1/blogs/{id} - Supprimer un blog</li>
 * </ul>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/blogs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Blogs", 
    description = """
        ## API de gestion des Blogs
        
        Les blogs sont des articles sur les héros, vilains et anti-héros. Cette API permet de:
        
        - Créer de nouveaux articles de blog
        - Consulter la liste complète des articles
        - Rechercher un article spécifique par son identifiant
        - Mettre à jour le contenu d'un article
        - Supprimer un article du système
        
        ### Règles métier
        
        - Le titre est obligatoire
        - Le contenu est obligatoire
        - Un blog peut être associé à un héros, un vilain ou un anti-héros
        - L'identifiant est généré automatiquement (UUID)
        - La suppression est définitive
        """
)
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * Récupère la liste de tous les blogs.
     *
     * @return une liste de tous les blogs
     */
    @GetMapping
    @Operation(
        summary = "Récupérer tous les blogs",
        description = """
            Retourne la liste complète de tous les articles de blog enregistrés dans le système.
            
            ## Exemple de réponse
            
            ```json
            [
              {
                "id": "550e8400-e29b-41d4-a716-446655440000",
                "title": "Introduction à Spring Boot",
                "body": "Spring Boot est un framework qui simplifie...",
                "author": "John Doe"
              }
            ]
            ```
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Liste des blogs récupérée avec succès",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Blog.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(ref = "#/components/schemas/ErrorResponse")
            )
        )
    })
    public ResponseEntity<Iterable<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.findAllBlogs());
    }

    /**
     * Récupère un blog par son identifiant unique.
     *
     * @param id l'identifiant UUID du blog
     * @return le blog correspondant à l'identifiant
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Récupérer un blog par son ID",
        description = """
            Retourne les détails d'un article de blog spécifique identifié par son UUID.
            
            ## Paramètres
            
            - `id`: Identifiant unique du blog (format UUID)
            
            ## Erreurs possibles
            
            - `404 Not Found`: Aucun blog trouvé avec cet identifiant
            - `400 Bad Request`: Format d'identifiant invalide
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Blog trouvé et retourné avec succès",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Blog.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Blog non trouvé",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(example = """
                    {
                      "timestamp": "2024-01-15T10:30:00Z",
                      "status": 404,
                      "error": "Not Found",
                      "message": "Blog by id 550e8400-e29b-41d4-a716-446655440000 was not found",
                      "path": "/api/v1/blogs/550e8400-e29b-41d4-a716-446655440000"
                    }
                    """)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Format d'identifiant invalide",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    public ResponseEntity<Blog> getBlogById(
            @Parameter(
                name = "id",
                description = "Identifiant unique du blog (UUID)",
                required = true,
                example = "550e8400-e29b-41d4-a716-446655440000"
            )
            @PathVariable UUID id) {
        return ResponseEntity.ok(blogService.findBlogById(id));
    }

    /**
     * Crée un nouveau blog.
     *
     * @param blogDto les données du blog à créer
     * @return le blog créé avec son identifiant généré
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Créer un nouveau blog",
        description = """
            Crée un nouvel article de blog dans le système avec les données fournies.
            
            ## Corps de la requête
            
            ```json
            {
              "title": "Introduction à Spring Boot",
              "body": "Spring Boot est un framework qui simplifie le développement d'applications Java...",
              "author": "John Doe"
            }
            ```
            
            ## Règles de validation
            
            - `title`: Obligatoire, entre 5 et 200 caractères
            - `body`: Obligatoire, minimum 10 caractères
            - `author`: Optionnel, maximum 100 caractères
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Blog créé avec succès",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Blog.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données de requête invalides",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(example = """
                    {
                      "timestamp": "2024-01-15T10:30:00Z",
                      "status": 400,
                      "error": "Bad Request",
                      "message": "Validation failed",
                      "errors": [
                        {"field": "title", "message": "Le titre est obligatoire"},
                        {"field": "body", "message": "Le contenu doit contenir au moins 10 caractères"}
                      ]
                    }
                    """)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    public ResponseEntity<Blog> createBlog(
            @Parameter(
                name = "blogDto",
                description = "Données du blog à créer",
                required = true
            )
            @Valid @RequestBody BlogDto blogDto) {
        Blog createdBlog = blogService.createBlog(blogDto);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    /**
     * Met à jour un blog existant.
     *
     * @param id l'identifiant du blog à mettre à jour
     * @param blogDto les nouvelles données du blog
     * @return le blog mis à jour
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Mettre à jour un blog",
        description = """
            Met à jour le contenu d'un article de blog existant.
            
            ## Paramètres
            
            - `id`: Identifiant unique du blog à mettre à jour
            
            ## Corps de la requête
            
            Tous les champs fournis seront mis à jour. Les champs non fournis resteront inchangés.
            
            ```json
            {
              "title": "Guide avancé Spring Boot",
              "body": "Ce guide couvre les fonctionnalités avancées de Spring Boot...",
              "author": "Jane Doe"
            }
            ```
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Blog mis à jour avec succès",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Blog.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Blog non trouvé",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Données de requête invalides",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    public ResponseEntity<Blog> updateBlog(
            @Parameter(
                name = "id",
                description = "Identifiant unique du blog à mettre à jour",
                required = true,
                example = "550e8400-e29b-41d4-a716-446655440000"
            )
            @PathVariable UUID id,
            @Parameter(
                name = "blogDto",
                description = "Nouvelles données du blog",
                required = true
            )
            @Valid @RequestBody BlogDto blogDto) {
        Blog updatedBlog = blogService.updateBlog(id, blogDto);
        return ResponseEntity.ok(updatedBlog);
    }

    /**
     * Supprime un blog par son identifiant.
     *
     * @param id l'identifiant du blog à supprimer
     * @return réponse sans contenu (204)
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un blog",
        description = """
            Supprime définitivement un article de blog du système.
            
            ## Avertissement
            
            Cette action est **irréversible**. Une fois supprimé, l'article ne peut pas être récupéré.
            
            ## Paramètres
            
            - `id`: Identifiant unique du blog à supprimer
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Blog supprimé avec succès"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Blog non trouvé",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    public ResponseEntity<Void> deleteBlog(
            @Parameter(
                name = "id",
                description = "Identifiant unique du blog à supprimer",
                required = true,
                example = "550e8400-e29b-41d4-a716-446655440000"
            )
            @PathVariable UUID id) {
        blogService.removeBlogById(id);
        return ResponseEntity.noContent().build();
    }
}
