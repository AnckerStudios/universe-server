package com.example.universe.controller;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;
import com.example.universe.model.PlanetSystem;
import com.example.universe.model.Satellite;
import com.example.universe.service.PlanetSystemService;
import com.example.universe.service.SatelliteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/satellite")
public class SatelliteController {
    private final SatelliteService satelliteService;

    public SatelliteController(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<SatelliteEntity> getSatelliteById(@PathVariable("id")String id){
        SatelliteEntity satellite = satelliteService.findSatelliteById(UUID.fromString(id));
        return new ResponseEntity<>(satellite,HttpStatus.OK);
    }
   /* @GetMapping("/all")
    public ResponseEntity<List<PlanetSystem>> getAllPlanetSystem(){
        List<PlanetSystem> planetSystems = planetSystemService.findAllPlanetSystem();
        return new ResponseEntity<>(planetSystems, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<PlanetSystemEntity> getPlanetSystemById(@PathVariable("id") UUID id){
        PlanetSystemEntity planetSystem = planetSystemService.findPlanetSystemById(id);
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
    }*/
}
