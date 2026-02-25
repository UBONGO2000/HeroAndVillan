package com.springbootlearning.learningspringboot.backend.controller;

import com.springbootlearning.learningspringboot.backend.dto.VillainDto;
import com.springbootlearning.learningspringboot.backend.entity.Villain;
import com.springbootlearning.learningspringboot.backend.service.VillainService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/villains")
public class VillainController {

    private final VillainService villainService;

    public VillainController(VillainService villainService) {
        this.villainService = villainService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Villain>> getAllVillains() {
        return ResponseEntity.ok(villainService.findAllVillains());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Villain> getVillainById(@PathVariable UUID id) {
        return ResponseEntity.ok(villainService.findVillainById(id));
    }

    @PostMapping
    public ResponseEntity<Villain> createVillain(@Valid @RequestBody VillainDto villainDto) {
        Villain createdVillain = villainService.createVillain(villainDto);
        return new ResponseEntity<>(createdVillain, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Villain> updateVillain(@PathVariable UUID id, @Valid @RequestBody VillainDto villainDto) {
        Villain updatedVillain = villainService.updateVillain(id, villainDto);
        return ResponseEntity.ok(updatedVillain);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVillain(@PathVariable UUID id) {
        villainService.removeVillainById(id);
        return ResponseEntity.noContent().build();
    }
}