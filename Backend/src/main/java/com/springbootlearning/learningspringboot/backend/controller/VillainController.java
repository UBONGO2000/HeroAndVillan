package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.VillainDto;
import com.springbootlearning.learningspringboot.backend.entity.Villain;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.service.VillainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Contrôleur REST pour la gestion des Villains.
 * 
 * <p>Ce contrôleur expose les endpoints CRUD pour la gestion des entités Villain.
 * Tous les endpoints sont versionnés avec le préfixe `/api/v1/`.</p>
 * 
 * <h2>Endpoints disponibles:</h2>
 * <ul>
 *   <li>GET /api/v1/villains - Récupérer tous les villains</li>
 *   <li>GET /api/v1/villains/{id} - Récupérer un villain par ID</li>
 *   <li>POST /api/v1/villains - Créer un nouveau villain</li>
 *   <li>PUT /api/v1/villains/{id} - Mettre à jour un villain</li>
 *   <li>DELETE /api/v1/villains/{id} - Supprimer un villain</li>
 * </ul>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/api/v1/villains", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
    name = "Villains", 
    description = """
        ## API de gestion des Villains
        
        Les villains sont les antagonistes du système CMMS. Cette API permet de:
        
        - Créer de nouveaux villains avec leurs caractéristiques
        - Consulter la liste complète des villains
        - Rechercher un villain spécifique par son identifiant
        - Mettre à jour les informations d'un villain
        - Supprimer un villain du système
        
        ### Règles métier
        
        - Le prénom et le nom sont obligatoires
        - L'identifiant est généré automatiquement (UUID)
        - La suppression est définitive
        """
)
public class VillainController {

    private final VillainService villainService;

    public VillainController(VillainService villainService) {
        this.villainService = villainService;
    }

    /**
     * Récupère la liste de tous les villains.
     *
     * @return une liste de tous les villains
     */
    @GetMapping
    @Operation(
        summary = "Récupérer tous les villains",
        description = """
            Retourne la liste complète de tous les villains enregistrés dans le système.
            
            ## Exemple de réponse
            
            ```json
            [
              {
                "id": "550e8400-e29b-41d4-a716-446655440000",
                "firstName": "Lord",
                "lastName": "Voldemort",
                "house": "Slytherin",
                "knownAs": "He-Who-Must-Not-Be-Named"
              }
            ]
            ```
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Liste des villains récupérée avec succès",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Villain.class)
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
    public ResponseEntity<Iterable<Villain>> getAllVillains() {
        return ResponseEntity.ok(villainService.findAllVillains());
    }

    /**
     * Récupère un villain par son identifiant unique.
     *
     * @param id l'identifiant UUID du villain
     * @return le villain correspondant à l'identifiant
     * @throws NotFoundException si aucun villain n'est trouvé
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Récupérer un villain par son ID",
        description = """
            Retourne les détails d'un villain spécifique identifié par son UUID.
            
            ## Paramètres
            
            - `id`: Identifiant unique du villain (format UUID)
            
            ## Erreurs possibles
            
            - `404 Not Found`: Aucun villain trouvé avec cet identifiant
            - `400 Bad Request`: Format d'identifiant invalide
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Villain trouvé et retourné avec succès",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Villain.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Villain non trouvé",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(example = """
                    {
                      "timestamp": "2024-01-15T10:30:00Z",
                      "status": 404,
                      "error": "Not Found",
                      "message": "Villain by id 550e8400-e29b-41d4-a716-446655440000 was not found",
                      "path": "/api/v1/villains/550e8400-e29b-41d4-a716-446655440000"
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
    public ResponseEntity<Villain> getVillainById(
            @Parameter(
                name = "id",
                description = "Identifiant unique du villain (UUID)",
                required = true,
                example = "550e8400-e29b-41d4-a716-446655440000"
            )
            @PathVariable UUID id) {
        return ResponseEntity.ok(villainService.findVillainById(id));
    }

    /**
     * Crée un nouveau villain.
     *
     * @param villainDto les données du villain à créer
     * @return le villain créé avec son identifiant généré
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Créer un nouveau villain",
        description = """
            Crée un nouveau villain dans le système avec les données fournies.
            
            ## Corps de la requête
            
            ```json
            {
              "firstName": "Lord",
              "lastName": "Voldemort",
              "house": "Slytherin",
              "knownAs": "He-Who-Must-Not-Be-Named"
            }
            ```
            
            ## Règles de validation
            
            - `firstName`: Obligatoire, entre 2 et 50 caractères
            - `lastName`: Obligatoire, entre 2 et 50 caractères
            - `house`: Optionnel, maximum 50 caractères
            - `knownAs`: Optionnel, maximum 100 caractères
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Villain créé avec succès",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Villain.class)
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
                        {"field": "firstName", "message": "Le prénom est obligatoire"},
                        {"field": "lastName", "message": "Le nom doit contenir entre 2 et 50 caractères"}
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
    public ResponseEntity<Villain> createVillain(
            @Parameter(
                name = "villainDto",
                description = "Données du villain à créer",
                required = true
            )
            @Valid @RequestBody VillainDto villainDto) {
        Villain createdVillain = villainService.createVillain(villainDto);
        return new ResponseEntity<>(createdVillain, HttpStatus.CREATED);
    }

    /**
     * Met à jour un villain existant.
     *
     * @param id l'identifiant du villain à mettre à jour
     * @param villainDto les nouvelles données du villain
     * @return le villain mis à jour
     * @throws NotFoundException si aucun villain n'est trouvé
     */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Mettre à jour un villain",
        description = """
            Met à jour les informations d'un villain existant.
            
            ## Paramètres
            
            - `id`: Identifiant unique du villain à mettre à jour
            
            ## Corps de la requête
            
            Tous les champs fournis seront mis à jour. Les champs non fournis resteront inchangés.
            
            ```json
            {
              "firstName": "Tom",
              "lastName": "Riddle",
              "house": "Slytherin",
              "knownAs": "Lord Voldemort"
            }
            ```
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Villain mis à jour avec succès",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Villain.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Villain non trouvé",
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
    public ResponseEntity<Villain> updateVillain(
            @Parameter(
                name = "id",
                description = "Identifiant unique du villain à mettre à jour",
                required = true,
                example = "550e8400-e29b-41d4-a716-446655440000"
            )
            @PathVariable UUID id,
            @Parameter(
                name = "villainDto",
                description = "Nouvelles données du villain",
                required = true
            )
            @Valid @RequestBody VillainDto villainDto) {
        Villain updatedVillain = villainService.updateVillain(id, villainDto);
        return ResponseEntity.ok(updatedVillain);
    }

    /**
     * Supprime un villain par son identifiant.
     *
     * @param id l'identifiant du villain à supprimer
     * @return réponse sans contenu (204)
     * @throws NotFoundException si aucun villain n'est trouvé
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Supprimer un villain",
        description = """
            Supprime définitivement un villain du système.
            
            ## Avertissement
            
            Cette action est **irréversible**. Une fois supprimé, le villain ne peut pas être récupéré.
            
            ## Paramètres
            
            - `id`: Identifiant unique du villain à supprimer
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Villain supprimé avec succès"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Villain non trouvé",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erreur interne du serveur",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    public ResponseEntity<Void> deleteVillain(
            @Parameter(
                name = "id",
                description = "Identifiant unique du villain à supprimer",
                required = true,
                example = "550e8400-e29b-41d4-a716-446655440000"
            )
            @PathVariable UUID id) {
        villainService.removeVillainById(id);
        return ResponseEntity.noContent().build();
    }
}
