package com.example.universe.controller;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.model.PlanetSystem;
import com.example.universe.service.PlanetSystemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/universe")
public class PlanetSystemController {
    private final PlanetSystemService planetSystemService;
    private static final Logger logger = LogManager.getLogger();

    public PlanetSystemController(PlanetSystemService planetSystemService) {
        this.planetSystemService = planetSystemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlanetSystem>> getAllPlanetSystem(){
        List<PlanetSystem> planetSystems = planetSystemService.findAllPlanetSystem();
        logger.debug("PlanetSystem list founded successfully");
        return new ResponseEntity<>(planetSystems, HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<PlanetSystemEntity> getPlanetSystemById(@PathVariable("id")String id){
        PlanetSystemEntity planetSystem = planetSystemService.findPlanetSystemById(UUID.fromString(id));
        logger.debug("PlanetSystem by " + id + " founded successfully");
        return new ResponseEntity<>(planetSystem,HttpStatus.OK);
    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<PlanetSystemEntity> getPlanetSystemByName(@PathVariable("name")String name){
        PlanetSystemEntity planetSystem = planetSystemService.findPlanetSystemByName(name);
        logger.debug("PlanetSystem name " + name + " founded successfully");
        return new ResponseEntity<>(planetSystem,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<PlanetSystemEntity> addPlanetSystem(@RequestBody PlanetSystemEntity planetSystem){
        PlanetSystemEntity newPlanetSystem = planetSystemService.addPlanetSystem(planetSystem);
        logger.debug("PlanetSystem " + planetSystem.getName() + " added successfully");
        return new ResponseEntity<>(newPlanetSystem,HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<PlanetSystemEntity> updatePlanetSystem(@RequestBody PlanetSystemEntity planetSystem){
        PlanetSystemEntity updatePlanetSystem = planetSystemService.updatePlanetSystem(planetSystem);
        logger.debug("PlanetSystem " + planetSystem.getName() + " updated successfully");
        return new ResponseEntity<>(updatePlanetSystem,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public void deletePlanetSystem(@PathVariable("id")UUID id) throws IllegalAccessException{
        try {
            logger.debug("Creature deleted successfully");
            planetSystemService.deletePlanetSystem(id);
        } catch (Exception e){
            logger.error("Error!" + e);
            throw new IllegalAccessException("Error! Cant delete PlanetSystem with id " + id);
        }

    }
}
