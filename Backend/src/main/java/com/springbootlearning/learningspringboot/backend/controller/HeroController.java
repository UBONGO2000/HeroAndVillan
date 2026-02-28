package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.HeroDto;
import com.springbootlearning.learningspringboot.backend.service.HeroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des Héros.
 * 
 * <p>Ce contrôleur expose les endpoints API pour les opérations CRUD
 * sur les héros (Spider-Man, Iron Man, Superman, etc.).</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/heroes")
@Tag(name = "Heroes", description = "API de gestion des héros")
public class HeroController {

    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    /**
     * Récupère tous les héros avec pagination.
     * 
     * @param pageable les informations de pagination (page, size, sort)
     * @return une page de héros
     */
    @Operation(summary = "Récupérer tous les héros (paginé)", 
               description = "Retourne la liste paginée de tous les héros")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des héros récupérée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = HeroDto.class)))
    })
    @GetMapping
    public ResponseEntity<Page<HeroDto>> getAllHeroes(
            @Parameter(description = "Paramètres de pagination (page, size, sort)")
            @PageableDefault(size = 10, sort = "firstName") Pageable pageable) {
        Page<HeroDto> heroes = heroService.findAllHeroes(pageable);
        return ResponseEntity.ok(heroes);
    }

    /**
     * Récupère tous les héros sans pagination (pour compatibilité).
     * 
     * @return la liste de tous les héros
     */
    @Operation(summary = "Récupérer tous les héros (liste complète)", 
               description = "Retourne la liste complète de tous les héros")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des héros récupérée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = HeroDto.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<HeroDto>> getAllHeroesList() {
        List<HeroDto> heroes = heroService.findAllHeroes();
        return ResponseEntity.ok(heroes);
    }

    /**
     * Récupère un héros par son identifiant.
     * 
     * @param id l'identifiant du héros
     * @return le héros trouvé
     */
    @Operation(summary = "Récupérer un héros par ID", 
               description = "Retourne un héros unique identifié par son UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Héros trouvé",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = HeroDto.class))),
        @ApiResponse(responseCode = "404", description = "Héros non trouvé", 
                     content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<HeroDto> getHeroById(
            @Parameter(description = "UUID du héros à récupérer") 
            @PathVariable UUID id) {
        HeroDto hero = heroService.findHeroById(id);
        return ResponseEntity.ok(hero);
    }

    /**
     * Crée un nouveau héros.
     * 
     * @param heroDto les données du héros à créer
     * @return le héros créé
     */
    @Operation(summary = "Créer un nouveau héros", 
               description = "Crée un nouveau héros avec ses caractéristiques (pouvoir, affiliation, rating)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Héros créé avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = HeroDto.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content),
        @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", 
                     content = @Content)
    })
    @PostMapping
    public ResponseEntity<HeroDto> createHero(
            @Parameter(description = "Données du héros à créer") 
            @Valid @RequestBody HeroDto heroDto) {
        HeroDto hero = heroService.createHero(heroDto);
        return new ResponseEntity<>(hero, HttpStatus.CREATED);
    }

    /**
     * Met à jour un héros existant.
     * 
     * @param id l'identifiant du héros à mettre à jour
     * @param heroDto les nouvelles données du héros
     * @return le héros mis à jour
     */
    @Operation(summary = "Mettre à jour un héros", 
               description = "Met à jour les informations d'un héros existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Héros mis à jour avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = HeroDto.class))),
        @ApiResponse(responseCode = "404", description = "Héros ou catégorie non trouvé", 
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<HeroDto> updateHero(
            @Parameter(description = "UUID du héros à mettre à jour") 
            @PathVariable UUID id,
            @Parameter(description = "Nouvelles données du héros") 
            @Valid @RequestBody HeroDto heroDto) {
        HeroDto hero = heroService.updateHero(id, heroDto);
        return ResponseEntity.ok(hero);
    }

    /**
     * Supprime un héros.
     * 
     * @param id l'identifiant du héros à supprimer
     * @return réponse sans contenu
     */
    @Operation(summary = "Supprimer un héros", 
               description = "Supprime un héros par son identifiant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Héros supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Héros non trouvé", 
                     content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHero(
            @Parameter(description = "UUID du héros à supprimer") 
            @PathVariable UUID id) {
        heroService.removeHeroById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Recherche des héros par catégorie.
     * 
     * @param categoryId l'identifiant de la catégorie
     * @return la liste des héros de cette catégorie
     */
    @Operation(summary = "Récupérer les héros par catégorie", 
               description = "Retourne tous les héros appartenant à une catégorie donnée")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des héros récupérée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = HeroDto.class)))
    })
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<HeroDto>> getHeroesByCategory(
            @Parameter(description = "UUID de la catégorie") 
            @PathVariable UUID categoryId) {
        List<HeroDto> heroes = heroService.findHeroesByCategory(categoryId);
        return ResponseEntity.ok(heroes);
    }
}
