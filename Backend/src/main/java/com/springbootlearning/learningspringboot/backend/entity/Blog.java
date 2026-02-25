package com.springbootlearning.learningspringboot.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "blog")
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Body is required")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @NotBlank(message = "Author is required")
    @Column(nullable = false)
    private String author;
}
