package com.example.universe.service;


import com.example.universe.exeption.PlanetSystemNotFoundExeption;
import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.model.PlanetSystem;
import com.example.universe.repo.PlanetSystemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlanetSystemService {
    private final PlanetSystemRepo planetSystemRepo;

    @Autowired
    public PlanetSystemService(PlanetSystemRepo planetSystemRepo){
        this.planetSystemRepo = planetSystemRepo;
    }

    public PlanetSystemEntity addPlanetSystem(PlanetSystemEntity planetSystem){
        planetSystem.setId(UUID.randomUUID());
        return planetSystemRepo.save(planetSystem);
    }
    public List<PlanetSystem> findAllPlanetSystem(){
        return planetSystemRepo.findAll().stream().map(s -> PlanetSystem.toModel(s)).collect(Collectors.toList());
    }
    public PlanetSystemEntity updatePlanetSystem(PlanetSystemEntity planetSystem){
        return planetSystemRepo.save(planetSystem);
    }
    public PlanetSystemEntity findPlanetSystemById(UUID id){
        return planetSystemRepo.findPlanetSystemById(id).orElseThrow(() -> new PlanetSystemNotFoundExeption("PlanetSystem by id "+id+" was not found"));
    }

    public void deletePlanetSystem(UUID id){
        planetSystemRepo.deleteById(id);
    }

    public PlanetSystemEntity findPlanetSystemByName(String name) {
        return (PlanetSystemEntity) planetSystemRepo.findPlanetSystemByName(name).orElseThrow(() -> new PlanetSystemNotFoundExeption("PlanetSystem by name "+name+" was not found"));
    }
}
