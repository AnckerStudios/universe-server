package com.example.universe.controller;

import com.example.universe.entity.PlanetSystemEntity;
//import com.example.universe.entity.User;
import com.example.universe.exeption.PlanetSystemNotFoundExeption;
import com.example.universe.DBReadSave;
import com.example.universe.exeption.SatelliteNotFoundExeption;
import com.example.universe.model.*;
import com.example.universe.service.PlanetSystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
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
    public ResponseEntity<PlanetSystem> getPlanetSystemById(@PathVariable("id")UUID id){
        PlanetSystem planetSystem = PlanetSystem.toModel(planetSystemService.findPlanetSystemById(id));
        return new ResponseEntity<>(planetSystem,HttpStatus.OK);
    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<PlanetSystem>> getPlanetSystemByName(@PathVariable("name")String name){
        List<PlanetSystem> planetSystems = planetSystemService.findPlanetSystemByName(name).stream().map(PlanetSystem::toModel).collect(Collectors.toList());
        if(planetSystems.size() == 0)
            throw new PlanetSystemNotFoundExeption("Planet system by name "+name+" was not found");
        return new ResponseEntity<>(planetSystems,HttpStatus.OK);
    }
    @PostMapping("/findByCoords")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<PlanetSystem>> getPlanetSystemByCoords(@RequestBody Coords coords){
        List<PlanetSystem> planetSystems = planetSystemService.findPlanetSystemByCoords(coords.getCoordX(), coords.getCoordY(), coords.getRange());
        if(planetSystems.size() == 0)
            throw new PlanetSystemNotFoundExeption("No planetary system detected near coordinates "+ coords.getCoordX() + " "+ coords.getCoordY());
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

    @GetMapping("/save/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void savePS(@PathVariable("id")String id){
        PlanetSystemEntity planetSystem = planetSystemService.findPlanetSystemById(UUID.fromString(id));
        DBReadSave e = new DBReadSave();
        try {
            e.savePS(planetSystem);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    @PostMapping("/load")
    @PreAuthorize("hasRole('ADMIN')")
    public void readPS(@RequestBody String name){
        DBReadSave e = new DBReadSave();
        System.out.println("name "+name);
        name = name.substring(0,name.length()-1);
        System.out.println("name "+name);
        try {
            PlanetSystemEntity ps = e.readPS(name);
            //PlanetSystemEntity newPlanetSystem = planetSystemService.addPlanetSystem(ps);
            try {
                PlanetSystemEntity oldSat = planetSystemService.findPlanetSystemById(ps.getId());
                boolean check = true;
                if(check){
                    System.out.println("замена");
                    planetSystemService.deletePlanetSystem(ps.getId());
                    PlanetSystemEntity newPlanetSystem = planetSystemService.addPlanetSystem(ps);
                }
            }catch (PlanetSystemNotFoundExeption ex){
                PlanetSystemEntity newPlanetSystem = planetSystemService.addPlanetSystem(ps);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
