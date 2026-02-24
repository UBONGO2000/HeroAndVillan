package com.springbootlearning.learningspringboot.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Villain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY, generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "First name is required")
    private String firstName;

    private String lastName;

    private String house;

    private String knownAs;

}
