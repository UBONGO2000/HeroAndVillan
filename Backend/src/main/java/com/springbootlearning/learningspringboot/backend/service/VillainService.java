package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.VillainDto;
import com.springbootlearning.learningspringboot.backend.entity.Category;
import com.springbootlearning.learningspringboot.backend.entity.Villain;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.repository.CategoryRepository;
import com.springbootlearning.learningspringboot.backend.repository.VillainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Service pour la gestion des Villains.
 * 
 * <p>Ce service fournit les opérations CRUD pour les villains,
 * incluant la gestion des relations avec les catégories.</p>
 * 
 * @author Georges
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class VillainService {

    private static final Logger logger = LoggerFactory.getLogger(VillainService.class);
    private final VillainRepository villainRepository;
    private final CategoryRepository categoryRepository;

    public VillainService(VillainRepository villainRepository, CategoryRepository categoryRepository) {
        this.villainRepository = villainRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Récupère tous les villains.
     * 
     * @return un Iterable de tous les villains
     */
    public Iterable<Villain> findAllVillains() {
        return villainRepository.findAll();
    }

    /**
     * Récupère un villain par son identifiant.
     * 
     * @param id l'identifiant du villain
     * @return le villain trouvé
     * @throws NotFoundException si le villain n'est pas trouvé
     */
    public Villain findVillainById(UUID id) {
        return villainRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Villain by id " + id + " was not found")
        );
    }

    /**
     * Supprime un villain par son identifiant.
     * 
     * @param id l'identifiant du villain à supprimer
     */
    @Transactional
    public void removeVillainById(UUID id) {
        villainRepository.deleteById(id);
        logger.info("Villain deleted successfully with id: {}", id);
    }

    /**
     * Crée un nouveau villain.
     * 
     * @param villainDto les données du villain à créer
     * @return le villain créé
     * @throws NotFoundException si la catégorie spécifiée n'existe pas
     */
    @Transactional
    public Villain createVillain(VillainDto villainDto) {
        Villain villain = new Villain();

        updateVillainFromDto(villain, villainDto);

        Villain savedVillain = villainRepository.save(villain);
        logger.info("Villain created successfully with id: {}", savedVillain.getId());
        return savedVillain;
    }

    /**
     * Met à jour un villain existant.
     * 
     * @param id l'identifiant du villain à mettre à jour
     * @param villainDto les nouvelles données du villain
     * @return le villain mis à jour
     * @throws NotFoundException si le villain ou la catégorie n'est pas trouvé
     */
    @Transactional
    public Villain updateVillain(UUID id, VillainDto villainDto) {
        Villain villain = findVillainById(id);

        updateVillainFromDto(villain, villainDto);

        Villain updatedVillain = villainRepository.save(villain);
        logger.info("Villain updated successfully with id: {}", updatedVillain.getId());
        return updatedVillain;
    }

    /**
     * Met à jour les propriétés d'un villain à partir du DTO.
     * 
     * @param villain le villain à mettre à jour
     * @param villainDto les données source
     */
    private void updateVillainFromDto(Villain villain, VillainDto villainDto) {
        villain.setFirstName(villainDto.getFirstName());
        villain.setLastName(villainDto.getLastName());
        villain.setVillainName(villainDto.getVillainName());
        villain.setHouse(villainDto.getHouse());
        villain.setKnownAs(villainDto.getKnownAs());
        villain.setPower(villainDto.getPower());
        villain.setAffiliation(villainDto.getAffiliation());
        villain.setRating(villainDto.getRating());
        villain.setImageUrl(villainDto.getImageUrl());
        villain.setBiography(villainDto.getBiography());

        if (villainDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(villainDto.getCategoryId())
                    .orElseThrow(() -> new NotFoundException("Category by id " + villainDto.getCategoryId() + " was not found"));
            villain.setCategory(category);
        }
    }
}
