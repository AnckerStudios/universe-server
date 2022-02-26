package com.example.universe.controller;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.model.PlanetSystem;
import com.example.universe.service.PlanetSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/universe")
public class PlanetSystemController {
    private final PlanetSystemService planetSystemService;

    public PlanetSystemController(PlanetSystemService planetSystemService) {
        this.planetSystemService = planetSystemService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlanetSystem>> getAllPlanetSystem(){
        List<PlanetSystem> planetSystems = planetSystemService.findAllPlanetSystem();
        return new ResponseEntity<>(planetSystems, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<PlanetSystemEntity> getPlanetSystemById(@PathVariable("id")String id){
        System.out.println(id);
        System.out.println(UUID.fromString(id));
        PlanetSystemEntity planetSystem = planetSystemService.findPlanetSystemById(UUID.fromString(id));
        return new ResponseEntity<>(planetSystem,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<PlanetSystemEntity> addPlanetSystem(@RequestBody PlanetSystemEntity planetSystem){
        PlanetSystemEntity newPlanetSystem = planetSystemService.addPlanetSystem(planetSystem);
        return new ResponseEntity<>(newPlanetSystem,HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<PlanetSystemEntity> updatePlanetSystem(@RequestBody PlanetSystemEntity planetSystem){
        PlanetSystemEntity updatePlanetSystem = planetSystemService.updatePlanetSystem(planetSystem);
        return new ResponseEntity<>(updatePlanetSystem,HttpStatus.OK);
    }
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePlanetSystem(@PathVariable("id")UUID id){
        planetSystemService.deletPlanetSystem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/
}
