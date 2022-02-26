package com.example.universe.service;

import com.example.universe.entity.CreatureEntity;
import com.example.universe.exeption.CreatureNotFoundExeption;
import com.example.universe.model.Creature;
import com.example.universe.repo.CreatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreatureService {
    private final CreatureRepo CreatureRepo;

    @Autowired
    public CreatureService(CreatureRepo CreatureRepo) {
        this.CreatureRepo = CreatureRepo;
    }

    public CreatureEntity addOre(CreatureEntity Creature) {
        Creature.setId(UUID.randomUUID());
        return CreatureRepo.save(Creature);
    }

    public List<Creature> findAllOre() {
        return CreatureRepo.findAll().stream().map(s -> Creature.toModel(s)).collect(Collectors.toList());
    }

    public CreatureEntity updateOre(CreatureEntity ore) {
        return CreatureRepo.save(ore);
    }

    public CreatureEntity findOreById(UUID id) {
        return CreatureRepo.findOreById(id).orElseThrow(() -> new CreatureNotFoundExeption("Ore by id" + id + "was not found"));
    }
}
