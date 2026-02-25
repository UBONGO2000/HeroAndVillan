package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.dto.VillainDto;
import com.springbootlearning.learningspringboot.backend.entity.Villain;
import com.springbootlearning.learningspringboot.backend.exception.NotFoundException;
import com.springbootlearning.learningspringboot.backend.repository.VillainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class VillainService {

    private static final Logger logger = LoggerFactory.getLogger(VillainService.class);
    private final VillainRepository villainRepository;

    public VillainService(VillainRepository villainRepository) {
        this.villainRepository = villainRepository;
    }

    public Iterable<Villain> findAllVillains(){
        return villainRepository.findAll();
    }

    public Villain findVillainById(UUID id){
        return villainRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Villain by id "+id+" was not found")
        );
    }

    @Transactional
    public void removeVillainById(UUID id){
        villainRepository.deleteById(id);
    }

    @Transactional
    public Villain createVillain(VillainDto villainDto){
        Villain villain = new Villain();

        villain.setFirstName(villainDto.getFirstName());
        villain.setLastName(villainDto.getLastName());
        villain.setHouse(villainDto.getHouse());
        villain.setKnownAs(villainDto.getKnownAs());

        Villain savedVillain = villainRepository.save(villain);
        logger.info("Villain created successfully with id: {}", savedVillain.getId());
        return savedVillain;
    }

    @Transactional
    public Villain updateVillain(UUID id, VillainDto villainDto){
        Villain villain = findVillainById(id);

        villain.setFirstName(villainDto.getFirstName());
        villain.setLastName(villainDto.getLastName());
        villain.setHouse(villainDto.getHouse());
        villain.setKnownAs(villainDto.getKnownAs());
        
        Villain updatedVillain = villainRepository.save(villain);
        logger.info("Villain updated successfully with id: {}", updatedVillain.getId());
        return updatedVillain;
    }

}
