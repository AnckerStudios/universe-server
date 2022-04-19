package com.example.universe.controller;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;
import com.example.universe.exeption.SatelliteNotFoundExeption;
import com.example.universe.DBReadSave;
import com.example.universe.model.PlanetSystem;
import com.example.universe.model.Satellite;
import com.example.universe.service.SatelliteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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
    public ResponseEntity<Satellite> getSatelliteById(@PathVariable("id")UUID id){
        Satellite satellite = satelliteService.findSatelliteById(id);
        return new ResponseEntity<>(satellite,HttpStatus.OK);
    }
    @GetMapping("/findByName/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Satellite>> getSatelliteByName(@PathVariable("name")String name){
        List<Satellite> satellites = satelliteService.findSatelliteByName(name);
        if(satellites.size() == 0)
            throw new SatelliteNotFoundExeption("Satellite by name "+name+" was not found");
        return new ResponseEntity<>(satellites,HttpStatus.OK);
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
    @GetMapping("/save/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void savePS(@PathVariable("id")String id){
        Satellite satellite = satelliteService.findSatelliteById(UUID.fromString(id));
        DBReadSave e = new DBReadSave();
        try {
            e.saveSat(SatelliteEntity.toEntityLow(satellite));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    @PostMapping("/load")
    @PreAuthorize("hasRole('ADMIN')")
    public void readPS(@RequestBody PlanetSystem ps){
        DBReadSave e = new DBReadSave();
        try {
            SatelliteEntity sat = e.readSat("planetX");
            try {
                Satellite oldSat = satelliteService.findSatelliteById(sat.getId());
                boolean check = true;
                if(check){
                    System.out.println("замена");
                    satelliteService.deleteSatellite(sat.getId());
                    sat.setPlanetSystem(PlanetSystemEntity.toEntityLow(ps));
                    SatelliteEntity newSat = satelliteService.addSatellite(sat);
                }
            }catch (SatelliteNotFoundExeption ex){
                sat.setPlanetSystem(PlanetSystemEntity.toEntityLow(ps));
                SatelliteEntity newSat = satelliteService.addSatellite(sat);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

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
