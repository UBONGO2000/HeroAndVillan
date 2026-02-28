package com.springbootlearning.learningspringboot.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootlearning.learningspringboot.backend.dto.HeroDto;
import com.springbootlearning.learningspringboot.backend.service.HeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests d'intégration pour {@link HeroController}.
 *
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("HeroController Integration Tests")
class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HeroService heroService;

    private HeroDto heroDto;
    private UUID heroId;

    @BeforeEach
    void setUp() {
        heroId = UUID.randomUUID();
        heroDto = new HeroDto();
        heroDto.setId(heroId);
        heroDto.setFirstName("Peter");
        heroDto.setLastName("Parker");
        heroDto.setHeroName("Spider-Man");
        heroDto.setPower("Spider abilities");
        heroDto.setAffiliation("Avengers");
        heroDto.setRating(new BigDecimal("4.8"));
        heroDto.setCategoryName("Marvel");
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Should return paginated heroes when authenticated")
    void shouldReturnPaginatedHeroesWhenAuthenticated() throws Exception {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<HeroDto> heroes = Arrays.asList(heroDto);
        Page<HeroDto> heroPage = new PageImpl<>(heroes, pageable, 1);

        when(heroService.findAllHeroes(any(Pageable.class))).thenReturn(heroPage);

        // When & Then
        mockMvc.perform(get("/api/v1/heroes")
                .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content", hasSize(1)))
            .andExpect(jsonPath("$.content[0].heroName", is("Spider-Man")))
            .andExpect(jsonPath("$.content[0].firstName", is("Peter")));

        verify(heroService, times(1)).findAllHeroes(any(Pageable.class));
    }

    @Test
    @DisplayName("Should return 401 when accessing heroes without authentication")
    void shouldReturn401WhenAccessingHeroesWithoutAuthentication() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/heroes"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Should return hero by ID when found")
    void shouldReturnHeroByIdWhenFound() throws Exception {
        // Given
        when(heroService.findHeroById(heroId)).thenReturn(heroDto);

        // When & Then
        mockMvc.perform(get("/api/v1/heroes/{id}", heroId))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id", is(heroId.toString())))
            .andExpect(jsonPath("$.heroName", is("Spider-Man")))
            .andExpect(jsonPath("$.firstName", is("Peter")));

        verify(heroService, times(1)).findHeroById(heroId);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Should create hero when user has ADMIN role")
    void shouldCreateHeroWhenUserHasAdminRole() throws Exception {
        // Given
        when(heroService.createHero(any(HeroDto.class))).thenReturn(heroDto);

        // When & Then
        mockMvc.perform(post("/api/v1/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroDto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.heroName", is("Spider-Man")));

        verify(heroService, times(1)).createHero(any(HeroDto.class));
    }

    @Test
    @WithMockUser(roles = "USER")
    @DisplayName("Should return 403 when creating hero without ADMIN role")
    void shouldReturn403WhenCreatingHeroWithoutAdminRole() throws Exception {
        // When & Then
        mockMvc.perform(post("/api/v1/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroDto)))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Should update hero when user has ADMIN role")
    void shouldUpdateHeroWhenUserHasAdminRole() throws Exception {
        // Given
        when(heroService.updateHero(any(UUID.class), any(HeroDto.class))).thenReturn(heroDto);

        // When & Then
        mockMvc.perform(put("/api/v1/heroes/{id}", heroId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(heroDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.heroName", is("Spider-Man")));

        verify(heroService, times(1)).updateHero(any(UUID.class), any(HeroDto.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @DisplayName("Should delete hero when user has ADMIN role")
    void shouldDeleteHeroWhenUserHasAdminRole() throws Exception {
        // Given
        doNothing().when(heroService).removeHeroById(heroId);

        // When & Then
        mockMvc.perform(delete("/api/v1/heroes/{id}", heroId))
            .andExpect(status().isNoContent());

        verify(heroService, times(1)).removeHeroById(heroId);
    }
}
