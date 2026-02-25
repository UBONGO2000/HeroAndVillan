package com.springbootlearning.learningspringboot.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntiHeroDto {

    @NotBlank(message = "First Name is required")
    private String firstname;

    @NotBlank(message = "Last Name is required")
    private String lastname;

    private String house;

    private String knownAs;
}
