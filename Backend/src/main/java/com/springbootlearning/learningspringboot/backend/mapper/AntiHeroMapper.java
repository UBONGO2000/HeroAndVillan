package com.springbootlearning.learningspringboot.backend.mapper;

import com.springbootlearning.learningspringboot.backend.dto.AntiHeroDto;
import com.springbootlearning.learningspringboot.backend.entity.AntiHero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AntiHeroMapper {

    AntiHeroDto toDto(AntiHero antiHero);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AntiHero toEntity(AntiHeroDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(AntiHeroDto dto, @MappingTarget AntiHero entity);

    List<AntiHeroDto> toDtoList(List<AntiHero> antiHeroes);
}
