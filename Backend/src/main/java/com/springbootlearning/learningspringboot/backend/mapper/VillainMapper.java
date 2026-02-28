package com.springbootlearning.learningspringboot.backend.mapper;

import com.springbootlearning.learningspringboot.backend.dto.VillainDto;
import com.springbootlearning.learningspringboot.backend.entity.Villain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Mapper MapStruct pour la conversion entre Villain et VillainDto.
 */
@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface VillainMapper {

    VillainDto toDto(Villain villain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Villain toEntity(VillainDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(VillainDto dto, @MappingTarget Villain entity);

    List<VillainDto> toDtoList(List<Villain> villains);
}
