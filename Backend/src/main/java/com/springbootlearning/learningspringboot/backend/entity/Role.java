package com.springbootlearning.learningspringboot.backend.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Entité JPA représentant un rôle utilisateur dans le système.
 *
 * <p>Les rôles définissent les permissions des utilisateurs dans l'application.</p>
 *
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Role", description = "Entité représentant un rôle utilisateur")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    @Schema(name = "id", description = "Identifiant unique du rôle", accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @Column(unique = true, nullable = false, length = 50)
    @Schema(name = "name", description = "Nom du rôle", example = "ROLE_USER")
    private String name;

    @Column(length = 255)
    @Schema(name = "description", description = "Description du rôle")
    private String description;

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
