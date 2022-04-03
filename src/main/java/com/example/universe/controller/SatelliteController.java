package com.example.universe.controller;

import com.example.universe.entity.SatelliteEntity;
import com.example.universe.model.Coords;
import com.example.universe.model.ObjectOre;
import com.example.universe.model.PlanetSystem;
import com.example.universe.model.Satellite;
import com.example.universe.service.SatelliteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Satellite>> getAllSatellite(){
        List<Satellite> satellite = satelliteService.findAllSatellite();
        return new ResponseEntity<>(satellite, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Satellite> getSatelliteById(@PathVariable("id")String id){
        Satellite satellite = satelliteService.findSatelliteById(UUID.fromString(id));
        return new ResponseEntity<>(satellite,HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SatelliteEntity> addSatellite(@RequestBody Satellite satellite){
        System.out.println("s = "+satellite);
        System.out.println("str = "+satellite.getObjectOre());
        SatelliteEntity newSatellite = satelliteService.addSatellite(SatelliteEntity.toEntityHigh(satellite));
        return new ResponseEntity<>(newSatellite, HttpStatus.OK);
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SatelliteEntity> updateSatellite(@RequestBody Satellite satellite){
        SatelliteEntity updateSatellite = satelliteService.updateSatellite(SatelliteEntity.toEntityHigh(satellite));
        return new ResponseEntity<>(updateSatellite, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePlanetSystem(@PathVariable("id")UUID id){
        System.out.println("del "+id);
        satelliteService.deleteSatellite(id);
    }

    /*@PostMapping("/updateCoord")
    public ResponseEntity<List<PlanetSystem>> getPlanetSystemByCoords(@RequestBody Coords coords){
        List<PlanetSystem> planetSystems = planetSystemService.findPlanetSystemByCoords(coords.getCoordX(), coords.getCoordY(), coords.getRange());
        return new ResponseEntity<>(planetSystems,HttpStatus.OK);
    }*/
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
