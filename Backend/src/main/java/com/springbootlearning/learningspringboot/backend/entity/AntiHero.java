package com.springbootlearning.learningspringboot.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "anti_hero")
@NoArgsConstructor
@AllArgsConstructor
public class AntiHero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "First Name is required")
    @Column(nullable = false)
    private String firstname;

    @NotBlank(message = "Last Name is required")
    @Column(nullable = false)
    private String lastname;

    private String house;

    private String knownAs;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
