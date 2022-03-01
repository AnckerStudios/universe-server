package com.example.universe.controller;

import com.example.universe.entity.SatelliteEntity;
import com.example.universe.model.Satellite;
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

    @GetMapping("/all")
    public ResponseEntity<List<Satellite>> getAllSatellite(){
        List<Satellite> satellite = satelliteService.findAllSatellite();
        return new ResponseEntity<>(satellite, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<SatelliteEntity> getPlanetSystemById(@PathVariable("id") UUID id){
        SatelliteEntity planetSystem = satelliteService.findSatelliteById(id);
        return new ResponseEntity<>(planetSystem,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<SatelliteEntity> addSatellite(@RequestBody SatelliteEntity satellite){
        SatelliteEntity newSatellite = satelliteService.addSatellite(satellite);
        return new ResponseEntity<>(newSatellite, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<SatelliteEntity> updateSatellite(@RequestBody SatelliteEntity satellite){
        SatelliteEntity updateSatellite = satelliteService.updateSatellite(satellite);
        return new ResponseEntity<>(updateSatellite, HttpStatus.OK);
    }
}
