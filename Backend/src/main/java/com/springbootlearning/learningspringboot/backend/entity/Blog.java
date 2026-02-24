package com.springbootlearning.learningspringboot.backend.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "BLOG")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY, generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String title;
    private String body;
    private String author;
}
