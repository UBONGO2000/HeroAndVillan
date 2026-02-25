package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.CommentDto;
import com.springbootlearning.learningspringboot.backend.entity.Comment;
import com.springbootlearning.learningspringboot.backend.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des Commentaires.
 * 
 * <p>Ce contrôleur expose les endpoints API pour les opérations CRUD
 * sur les commentaires des blogs.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "Comments", description = "API de gestion des commentaires")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Récupère tous les commentaires.
     * 
     * @return une Liste de tous les commentaires
     */
    @Operation(summary = "Récupérer tous les commentaires", 
               description = "Retourne la liste de tous les commentaires")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des commentaires récupérée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Comment.class)))
    })
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.findAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * Récupère un commentaire par son identifiant.
     * 
     * @param id l'identifiant du commentaire
     * @return le commentaire trouvé
     */
    @Operation(summary = "Récupérer un commentaire par ID", 
               description = "Retourne un commentaire unique identifié par son UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Commentaire trouvé",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Comment.class))),
        @ApiResponse(responseCode = "404", description = "Commentaire non trouvé", 
                     content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(
            @Parameter(description = "UUID du commentaire à récupérer") 
            @PathVariable UUID id) {
        Comment comment = commentService.findCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    /**
     * Récupère tous les commentaires d'un blog.
     * 
     * @param blogId l'identifiant du blog
     * @return une Liste des commentaires du blog
     */
    @Operation(summary = "Récupérer les commentaires d'un blog", 
               description = "Retourne tous les commentaires associés à un blog spécifique")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des commentaires du blog récupérée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Comment.class)))
    })
    @GetMapping("/blog/{blogId}")
    public ResponseEntity<List<Comment>> getCommentsByBlogId(
            @Parameter(description = "UUID du blog") 
            @PathVariable UUID blogId) {
        List<Comment> comments = commentService.findCommentsByBlogId(blogId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    /**
     * Crée un nouveau commentaire.
     * 
     * @param commentDto les données du commentaire à créer
     * @return le commentaire créé
     */
    @Operation(summary = "Créer un nouveau commentaire", 
               description = "Crée un nouveau commentaire sur un blog")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Commentaire créé avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Comment.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content),
        @ApiResponse(responseCode = "404", description = "Blog non trouvé", 
                     content = @Content)
    })
    @PostMapping
    public ResponseEntity<Comment> createComment(
            @Parameter(description = "Données du commentaire à créer") 
            @Valid @RequestBody CommentDto commentDto) {
        Comment comment = commentService.createComment(commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    /**
     * Met à jour un commentaire existant.
     * 
     * @param id l'identifiant du commentaire à mettre à jour
     * @param commentDto les nouvelles données du commentaire
     * @return le commentaire mis à jour
     */
    @Operation(summary = "Mettre à jour un commentaire", 
               description = "Met à jour le contenu d'un commentaire existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Commentaire mis à jour avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Comment.class))),
        @ApiResponse(responseCode = "404", description = "Commentaire ou blog non trouvé", 
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(
            @Parameter(description = "UUID du commentaire à mettre à jour") 
            @PathVariable UUID id,
            @Parameter(description = "Nouvelles données du commentaire") 
            @Valid @RequestBody CommentDto commentDto) {
        Comment comment = commentService.updateComment(id, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    /**
     * Supprime un commentaire.
     * 
     * @param id l'identifiant du commentaire à supprimer
     * @return réponse sans contenu
     */
    @Operation(summary = "Supprimer un commentaire", 
               description = "Supprime un commentaire par son identifiant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Commentaire supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Commentaire non trouvé", 
                     content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "UUID du commentaire à supprimer") 
            @PathVariable UUID id) {
        commentService.removeCommentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}