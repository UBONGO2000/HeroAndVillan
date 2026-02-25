package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.AntiHeroDto;
import com.springbootlearning.learningspringboot.backend.entity.AntiHero;
import com.springbootlearning.learningspringboot.backend.service.AntiHeroService;
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
 * Contrôleur REST pour la gestion des Anti-Héros.
 * 
 * <p>Ce contrôleur expose les endpoints API pour les opérations CRUD
 * sur les anti-héros (Deadpool, Venom, Punisher, etc.).</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/anti-heroes")
@Tag(name = "Anti-Heroes", description = "API de gestion des anti-héros")
public class AntiHeroController {

    private final AntiHeroService antiHeroService;

    public AntiHeroController(AntiHeroService antiHeroService) {
        this.antiHeroService = antiHeroService;
    }

    /**
     * Récupère tous les anti-héros.
     * 
     * @return un Iterable de tous les anti-héros
     */
    @Operation(summary = "Récupérer tous les anti-héros", 
               description = "Retourne la liste de tous les anti-héros")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des anti-héros récupérée avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = AntiHero.class)))
    })
    @GetMapping
    public ResponseEntity<Iterable<AntiHero>> getAllAntiHeroes() {
        Iterable<AntiHero> antiHeroes = antiHeroService.findAllAntiHeroes();
        return new ResponseEntity<>(antiHeroes, HttpStatus.OK);
    }

    /**
     * Récupère un anti-héros par son identifiant.
     * 
     * @param id l'identifiant de l'anti-héros
     * @return l'anti-héros trouvé
     */
    @Operation(summary = "Récupérer un anti-héros par ID", 
               description = "Retourne un anti-héros unique identifié par son UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Anti-héros trouvé",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = AntiHero.class))),
        @ApiResponse(responseCode = "404", description = "Anti-héros non trouvé", 
                     content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AntiHero> getAntiHeroById(
            @Parameter(description = "UUID de l'anti-héros à récupérer") 
            @PathVariable UUID id) {
        AntiHero antiHero = antiHeroService.findAntiHeroById(id);
        return new ResponseEntity<>(antiHero, HttpStatus.OK);
    }

    /**
     * Crée un nouvel anti-héros.
     * 
     * @param antiHeroDto les données de l'anti-héros à créer
     * @return l'anti-héros créé
     */
    @Operation(summary = "Créer un nouvel anti-héros", 
               description = "Crée un nouvel anti-héros avec ses caractéristiques (pouvoir, affiliation, rating)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Anti-héros créé avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = AntiHero.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content),
        @ApiResponse(responseCode = "404", description = "Catégorie non trouvée", 
                     content = @Content)
    })
    @PostMapping
    public ResponseEntity<AntiHero> createAntiHero(
            @Parameter(description = "Données de l'anti-héros à créer") 
            @Valid @RequestBody AntiHeroDto antiHeroDto) {
        AntiHero antiHero = antiHeroService.createAntiHero(antiHeroDto);
        return new ResponseEntity<>(antiHero, HttpStatus.CREATED);
    }

    /**
     * Met à jour un anti-héros existant.
     * 
     * @param id l'identifiant de l'anti-héros à mettre à jour
     * @param antiHeroDto les nouvelles données de l'anti-héros
     * @return l'anti-héros mis à jour
     */
    @Operation(summary = "Mettre à jour un anti-héros", 
               description = "Met à jour les informations d'un anti-héros existant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Anti-héros mis à jour avec succès",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = AntiHero.class))),
        @ApiResponse(responseCode = "404", description = "Anti-héros ou catégorie non trouvé", 
                     content = @Content),
        @ApiResponse(responseCode = "400", description = "Données invalides", 
                     content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AntiHero> updateAntiHero(
            @Parameter(description = "UUID de l'anti-héros à mettre à jour") 
            @PathVariable UUID id,
            @Parameter(description = "Nouvelles données de l'anti-héros") 
            @Valid @RequestBody AntiHeroDto antiHeroDto) {
        AntiHero antiHero = antiHeroService.updateAntiHero(id, antiHeroDto);
        return new ResponseEntity<>(antiHero, HttpStatus.OK);
    }

    /**
     * Supprime un anti-héros.
     * 
     * @param id l'identifiant de l'anti-héros à supprimer
     * @return réponse sans contenu
     */
    @Operation(summary = "Supprimer un anti-héros", 
               description = "Supprime un anti-héros par son identifiant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Anti-héros supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Anti-héros non trouvé", 
                     content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAntiHero(
            @Parameter(description = "UUID de l'anti-héros à supprimer") 
            @PathVariable UUID id) {
        antiHeroService.removeAntiHeroById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}