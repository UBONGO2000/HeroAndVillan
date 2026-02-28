package com.springbootlearning.learningspringboot.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * DTO pour la réponse JWT après authentification.
 *
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Réponse d'authentification JWT")
public class JwtResponse {

    @Schema(description = "Token JWT d'accès", example = "eyJhbGciOiJIUzI1NiIs...")
    private String token;

    @Schema(description = "Type de token", example = "Bearer")
    private String type = "Bearer";

    @Schema(description = "ID de l'utilisateur")
    private UUID id;

    @Schema(description = "Nom d'utilisateur", example = "johndoe")
    private String username;

    @Schema(description = "Adresse email", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Rôles de l'utilisateur", example = "[\"ROLE_USER\"]")
    private List<String> roles;

    public JwtResponse(String token, UUID id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
