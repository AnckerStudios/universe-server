package com.example.universe.service;


import com.example.universe.entity.SatelliteEntity;
import com.example.universe.exeption.PlanetSystemNotFoundExeption;
import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.model.Coords;
import com.example.universe.model.PlanetSystem;
import com.example.universe.repo.PlanetSystemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanetSystemService {
    private final PlanetSystemRepo planetSystemRepo;
    private EntityManager entityManager;
    @Autowired
    public PlanetSystemService(PlanetSystemRepo planetSystemRepo){
        this.planetSystemRepo = planetSystemRepo;
    }

    public PlanetSystemEntity addPlanetSystem(PlanetSystemEntity planetSystem){
        //planetSystem.setId(UUID.randomUUID());
        for(SatelliteEntity s : planetSystem.getSatellites()) {
            s.setPlanetSystem(planetSystem);
            //for(OreEntity o : s.getOres())
            //    o.
        }
        for(SatelliteEntity s : planetSystem.getSatellites())
            System.out.println(s.getPlanetSystem().getName());
        return planetSystemRepo.save(planetSystem);
    }
    public List<PlanetSystem> findAllPlanetSystem(){
        return planetSystemRepo.findAll().stream().map(s -> PlanetSystem.toModel(s)).collect(Collectors.toList());
    }
    public PlanetSystemEntity updatePlanetSystem(PlanetSystemEntity planetSystem){
        return planetSystemRepo.save(planetSystem);
    }
    public int updatePS(PlanetSystem planetSystem){
        return planetSystemRepo.updatePS(planetSystem.getId(), planetSystem.getXCoord(), planetSystem.getYCoord());
    }
    public PlanetSystemEntity findPlanetSystemById(UUID id){
        return planetSystemRepo.findPlanetSystemById(id).orElseThrow(() -> new PlanetSystemNotFoundExeption("PlanetSystem by id"+id+"was not found"));
    }


    public List<PlanetSystemEntity> findPlanetSystemByName(String name) {
        return planetSystemRepo.findPlanetSystemByName(name);
    }
    public void deletePlanetSystem(UUID id){
        planetSystemRepo.deleteById(id);
    }

    public List<PlanetSystem> findPlanetSystemByCoords(int coordX, int coordY, int range) {
        return planetSystemRepo.findPlanetSystemByCoords(coordX-range,coordX+range,coordY-range,coordY+range).stream().map(s -> PlanetSystem.toModel(s)).collect(Collectors.toList());
    }
}
