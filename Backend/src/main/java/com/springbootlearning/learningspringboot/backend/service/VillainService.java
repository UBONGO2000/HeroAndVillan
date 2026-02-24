package com.springbootlearning.learningspringboot.backend.service;

import com.springbootlearning.learningspringboot.backend.entity.Villain;
import com.springbootlearning.learningspringboot.backend.repository.VillanRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VillainService {

    private final VillanRepository villanRepository;

    public VillainService(VillanRepository villanRepository) {
        this.villanRepository = villanRepository;
    }

    public Iterable<Villain> findAllVillains(){
        return villanRepository.findAll();
    }


    public Optional<Villain> findVillainById(UUID id){
        return villanRepository.findById(id).orElseThrow(
                () -> new ChangeSetPersister.NotFoundException("Villain by id "+id+" was not found")
        );
    }

}
