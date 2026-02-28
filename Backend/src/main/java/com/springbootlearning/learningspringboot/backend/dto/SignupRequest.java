package com.springbootlearning.learningspringboot.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * DTO pour la requête d'inscription.
 *
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Requête d'inscription utilisateur")
public class SignupRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    @Schema(description = "Nom d'utilisateur", example = "johndoe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email
    @Schema(description = "Adresse email", example = "john.doe@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    @Schema(description = "Mot de passe", example = "password123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Rôles de l'utilisateur", example = "[\"ROLE_USER\"]")
    private Set<String> roles;
}
