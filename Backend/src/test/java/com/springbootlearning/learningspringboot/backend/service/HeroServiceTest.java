package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.HeroDto;
import com.springbootlearning.learningspringboot.backend.entity.Category;
import com.springbootlearning.learningspringboot.backend.entity.Hero;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.mapper.HeroMapper;
import com.springbootlearning.learningspringboot.backend.repository.CategoryRepository;
import com.springbootlearning.learningspringboot.backend.repository.HeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tests unitaires pour {@link HeroService}.
 *
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("HeroService Tests")
class HeroServiceTest {

    @Mock
    private HeroRepository heroRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private HeroMapper heroMapper;

    @InjectMocks
    private HeroService heroService;

    private Hero hero;
    private HeroDto heroDto;
    private Category category;
    private UUID heroId;

    @BeforeEach
    void setUp() {
        heroId = UUID.randomUUID();
        category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("Marvel");

        hero = new Hero();
        hero.setId(heroId);
        hero.setFirstName("Peter");
        hero.setLastName("Parker");
        hero.setHeroName("Spider-Man");
        hero.setPower("Spider abilities");
        hero.setAffiliation("Avengers");
        hero.setRating(new BigDecimal("4.8"));
        hero.setCategory(category);

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
    @DisplayName("Should return all heroes with pagination")
    void shouldReturnAllHeroesWithPagination() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Hero> heroes = Arrays.asList(hero);
        Page<Hero> heroPage = new PageImpl<>(heroes, pageable, 1);

        when(heroRepository.findAll(pageable)).thenReturn(heroPage);
        when(heroMapper.toDto(hero)).thenReturn(heroDto);

        // When
        Page<HeroDto> result = heroService.findAllHeroes(pageable);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getHeroName()).isEqualTo("Spider-Man");
        verify(heroRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Should return hero by ID when found")
    void shouldReturnHeroByIdWhenFound() {
        // Given
        when(heroRepository.findById(heroId)).thenReturn(Optional.of(hero));
        when(heroMapper.toDto(hero)).thenReturn(heroDto);

        // When
        HeroDto result = heroService.findHeroById(heroId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(heroId);
        assertThat(result.getHeroName()).isEqualTo("Spider-Man");
        verify(heroRepository, times(1)).findById(heroId);
    }

    @Test
    @DisplayName("Should throw NotFoundException when hero not found by ID")
    void shouldThrowNotFoundExceptionWhenHeroNotFound() {
        // Given
        when(heroRepository.findById(heroId)).thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> heroService.findHeroById(heroId))
            .isInstanceOf(NotFoundException.class)
            .hasMessageContaining("Hero not found with id");
        verify(heroRepository, times(1)).findById(heroId);
    }

    @Test
    @DisplayName("Should create hero successfully")
    void shouldCreateHeroSuccessfully() {
        // Given
        when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.of(category));
        when(heroMapper.toEntity(any(HeroDto.class))).thenReturn(hero);
        when(heroRepository.save(any(Hero.class))).thenReturn(hero);
        when(heroMapper.toDto(hero)).thenReturn(heroDto);

        // When
        HeroDto result = heroService.createHero(heroDto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getHeroName()).isEqualTo("Spider-Man");
        verify(heroRepository, times(1)).save(any(Hero.class));
    }

    @Test
    @DisplayName("Should update hero successfully")
    void shouldUpdateHeroSuccessfully() {
        // Given
        when(heroRepository.findById(heroId)).thenReturn(Optional.of(hero));
        when(categoryRepository.findById(any(UUID.class))).thenReturn(Optional.of(category));
        when(heroRepository.save(any(Hero.class))).thenReturn(hero);
        when(heroMapper.toDto(hero)).thenReturn(heroDto);

        // When
        HeroDto result = heroService.updateHero(heroId, heroDto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getHeroName()).isEqualTo("Spider-Man");
        verify(heroRepository, times(1)).findById(heroId);
        verify(heroRepository, times(1)).save(any(Hero.class));
    }

    @Test
    @DisplayName("Should delete hero successfully")
    void shouldDeleteHeroSuccessfully() {
        // Given
        doNothing().when(heroRepository).deleteById(heroId);

        // When
        heroService.removeHeroById(heroId);

        // Then
        verify(heroRepository, times(1)).deleteById(heroId);
    }

    @Test
    @DisplayName("Should return all heroes as list")
    void shouldReturnAllHeroesAsList() {
        // Given
        List<Hero> heroes = Arrays.asList(hero);
        when(heroRepository.findAll()).thenReturn(heroes);
        when(heroMapper.toDtoList(heroes)).thenReturn(Arrays.asList(heroDto));

        // When
        List<HeroDto> result = heroService.findAllHeroes();

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getHeroName()).isEqualTo("Spider-Man");
        verify(heroRepository, times(1)).findAll();
    }
}
