package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.HeroDto;
import com.springbootlearning.learningspringboot.backend.entity.Hero;
import com.springbootlearning.learningspringboot.backend.service.HeroService;
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
     * Récupère tous les héros.
     * 
     * @return un Iterable de tous les héros
     */
    @Operation(summary = "Récupérer tous les héros", 
               description = "Retourne la liste de tous les héros")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des héros récupérée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = Hero.class)))
    })
    @GetMapping
    public ResponseEntity<Iterable<Hero>> getAllHeroes() {
        Iterable<Hero> heroes = heroService.findAllHeroes();
        return new ResponseEntity<>(heroes, HttpStatus.OK);
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
                     schema = @Schema(implementation = Hero.class))),
        @ApiResponse(responseCode = "404", description = "Héros non trouvé", 
                     content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(
            @Parameter(description = "UUID du héros à récupérer") 
            @PathVariable UUID id) {
        Hero hero = heroService.findHeroById(id);
        return new ResponseEntity<>(hero, HttpStatus.OK);
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
                     schema = @Schema(implementation = Hero.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content),
        @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", 
                     content = @Content)
    })
    @PostMapping
    public ResponseEntity<Hero> createHero(
            @Parameter(description = "Données du héros à créer") 
            @Valid @RequestBody HeroDto heroDto) {
        Hero hero = heroService.createHero(heroDto);
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
                     schema = @Schema(implementation = Hero.class))),
        @ApiResponse(responseCode = "404", description = "Héros ou catégorie non trouvé", 
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Hero> updateHero(
            @Parameter(description = "UUID du héros à mettre à jour") 
            @PathVariable UUID id,
            @Parameter(description = "Nouvelles données du héros") 
            @Valid @RequestBody HeroDto heroDto) {
        Hero hero = heroService.updateHero(id, heroDto);
        return new ResponseEntity<>(hero, HttpStatus.OK);
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}