package com.springbootlearning.learningspringboot.backend.repository;

import com.springbootlearning.learningspringboot.backend.entity.Villain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface VillainRepository extends JpaRepository<Villain, UUID> {
}
