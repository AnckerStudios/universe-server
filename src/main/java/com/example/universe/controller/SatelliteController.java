package com.example.universe.controller;

import com.example.universe.entity.SatelliteEntity;
import com.example.universe.model.Satellite;
import com.example.universe.service.SatelliteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/satellite")
public class SatelliteController {
    private final SatelliteService satelliteService;
    private static final Logger logger = LogManager.getLogger();

    public SatelliteController(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Satellite>> getAllSatellite(){
        List<Satellite> satellite = satelliteService.findAllSatellite();
        logger.debug("Satellite list founded successfully");
        return new ResponseEntity<>(satellite, HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<SatelliteEntity> getSatelliteById(@PathVariable("id") UUID id){
        SatelliteEntity planetSystem = satelliteService.findSatelliteById(id);
        logger.debug("Satellite by " + id + " founded successfully");
        return new ResponseEntity<>(planetSystem,HttpStatus.OK);
    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<SatelliteEntity> getSatelliteByName(@PathVariable("name") String name){
        SatelliteEntity satellite = satelliteService.findSatelliteByName(name);
        logger.debug("Satellite by name " + name + " founded successfully");
        return new ResponseEntity<>(satellite, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<SatelliteEntity> addSatellite(@RequestBody SatelliteEntity satellite){
        SatelliteEntity newSatellite = satelliteService.addSatellite(satellite);
        logger.debug("Satellite " + satellite.getName() + " added successfully");
        return new ResponseEntity<>(newSatellite, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<SatelliteEntity> updateSatellite(@RequestBody SatelliteEntity satellite){
        SatelliteEntity updateSatellite = satelliteService.updateSatellite(satellite);
        logger.debug("Satellite " + satellite.getName() + " updated successfully");
        return new ResponseEntity<>(updateSatellite, HttpStatus.OK);
    }


}
