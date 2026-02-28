package com.springbootlearning.learningspringboot.backend.mapper;

import com.springbootlearning.learningspringboot.backend.dto.HeroDto;
import com.springbootlearning.learningspringboot.backend.entity.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Mapper MapStruct pour la conversion entre Hero et HeroDto.
 */
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface HeroMapper {

    /**
     * Convertit une entité Hero en DTO.
     *
     * @param hero l'entité à convertir
     * @return le DTO correspondant
     */
    HeroDto toDto(Hero hero);

    /**
     * Convertit un DTO en entité Hero.
     *
     * @param dto le DTO à convertir
     * @return l'entité correspondante
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Hero toEntity(HeroDto dto);

    /**
     * Met à jour une entité existante avec les valeurs du DTO.
     *
     * @param dto le DTO source
     * @param entity l'entité à mettre à jour
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(HeroDto dto, @MappingTarget Hero entity);

    /**
     * Convertit une liste d'entités en liste de DTOs.
     *
     * @param heroes la liste d'entités
     * @return la liste de DTOs
     */
    List<HeroDto> toDtoList(List<Hero> heroes);
}
