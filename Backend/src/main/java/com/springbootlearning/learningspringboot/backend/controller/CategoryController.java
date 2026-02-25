package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.CategoryDto;
import com.springbootlearning.learningspringboot.backend.entity.Category;
import com.springbootlearning.learningspringboot.backend.service.CategoryService;
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

import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des Catégories.
 * 
 * <p>Ce contrôleur expose les endpoints API pour les opérations CRUD
 * sur les catégories de personnages (Marvel, DC, Anime, etc.).</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories", description = "API de gestion des catégories de personnages")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Récupère toutes les catégories.
     * 
     * @return un Iterable de toutes les catégories
     */
    @Operation(summary = "Récupérer toutes les catégories", 
               description = "Retourne la liste de toutes les catégories de personnages")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des catégories récupérée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Category.class)))
    })
    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = categoryService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    /**
     * Récupère une catégorie par son identifiant.
     * 
     * @param id l'identifiant de la catégorie
     * @return la catégorie trouvée
     */
    @Operation(summary = "Récupérer une catégorie par ID", 
               description = "Retourne une catégorie unique identifiée par son UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catégorie trouvée",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Category.class))),
        @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", 
                     content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(
            @Parameter(description = "UUID de la catégorie à récupérer") 
            @PathVariable UUID id) {
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Crée une nouvelle catégorie.
     * 
     * @param categoryDto les données de la catégorie à créer
     * @return la catégorie créée
     */
    @Operation(summary = "Créer une nouvelle catégorie", 
               description = "Crée une nouvelle catégorie de personnages")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Catégorie créée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Category.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content)
    })
    @PostMapping
    public ResponseEntity<Category> createCategory(
            @Parameter(description = "Données de la catégorie à créer") 
            @Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    /**
     * Met à jour une catégorie existante.
     * 
     * @param id l'identifiant de la catégorie à mettre à jour
     * @param categoryDto les nouvelles données de la catégorie
     * @return la catégorie mise à jour
     */
    @Operation(summary = "Mettre à jour une catégorie", 
               description = "Met à jour les informations d'une catégorie existante")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Catégorie mise à jour avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Category.class))),
        @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", 
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @Parameter(description = "UUID de la catégorie à mettre à jour") 
            @PathVariable UUID id,
            @Parameter(description = "Nouvelles données de la catégorie") 
            @Valid @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.updateCategory(id, categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Supprime une catégorie.
     * 
     * @param id l'identifiant de la catégorie à supprimer
     * @return réponse sans contenu
     */
    @Operation(summary = "Supprimer une catégorie", 
               description = "Supprime une catégorie par son identifiant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Catégorie supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", 
                     content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @Parameter(description = "UUID de la catégorie à supprimer") 
            @PathVariable UUID id) {
        categoryService.removeCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
