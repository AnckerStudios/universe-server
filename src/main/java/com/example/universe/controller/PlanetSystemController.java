package com.example.universe.controller;

import com.example.universe.entity.PlanetSystemEntity;
//import com.example.universe.entity.User;
import com.example.universe.entity.SatelliteEntity;
import com.example.universe.model.Coords;
import com.example.universe.model.PlanetSystem;
import com.example.universe.model.UserModel;
import com.example.universe.service.PlanetSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/universe")
@CrossOrigin("*")
public class PlanetSystemController {
    private final PlanetSystemService planetSystemService;

    public PlanetSystemController(PlanetSystemService planetSystemService) {
        this.planetSystemService = planetSystemService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PlanetSystem>> getAllPlanetSystem(){
        List<PlanetSystem> planetSystems = planetSystemService.findAllPlanetSystem();
        return new ResponseEntity<>(planetSystems, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PlanetSystemEntity> getPlanetSystemById(@PathVariable("id")String id){
        PlanetSystemEntity planetSystem = planetSystemService.findPlanetSystemById(UUID.fromString(id));
        return new ResponseEntity<>(planetSystem,HttpStatus.OK);
    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<PlanetSystemEntity>> getPlanetSystemByName(@PathVariable("name")String name){
        //System.out.println(name);
        //System.out.println(UUID.fromString(name));
        List<PlanetSystemEntity> planetSystems = planetSystemService.findPlanetSystemByName(name);
        return new ResponseEntity<>(planetSystems,HttpStatus.OK);
    }
    @PostMapping("/findByCoords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PlanetSystem>> getPlanetSystemByCoords(@RequestBody Coords coords){
        System.out.println(coords.getCoordX()+" - "+coords.getCoordY()+" - "+coords.getRange());
        List<PlanetSystem> planetSystems = planetSystemService.findPlanetSystemByCoords(coords.getCoordX(), coords.getCoordY(), coords.getRange());
        return new ResponseEntity<>(planetSystems,HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlanetSystemEntity> addPlanetSystem(@RequestBody PlanetSystem planetSystem){
        PlanetSystemEntity newPlanetSystem = planetSystemService.addPlanetSystem(PlanetSystemEntity.toEntityHigh(planetSystem));
        return new ResponseEntity<>(newPlanetSystem,HttpStatus.CREATED);
    }
    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public void updatePlanetSystem(@RequestBody PlanetSystem planetSystem){
        System.out.println("upd = "+ planetSystem.getName()+ " " + planetSystem.getId()+ " "+ planetSystem.getSatellites());
        //PlanetSystemEntity e = PlanetSystemEntity.toEntityLow(planetSystem);
        //e.setSatellites(planetSystem.getSatellites().stream().map(SatelliteEntity::toEntityLow).collect(Collectors.toList()));
        //System.out.println("upd e = "+ e.getName()+ " " + e.getId()+ " "+ e.getSatellites());
        //PlanetSystemEntity updatePlanetSystem = planetSystemService.updatePlanetSystem(e);
        planetSystemService.updatePS(planetSystem);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePlanetSystem(@PathVariable("id")UUID id){
        planetSystemService.deletePlanetSystem(id);
    }

}
