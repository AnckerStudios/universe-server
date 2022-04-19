package com.example.universe.controller;

import com.example.universe.entity.ObjectOreEntity;
import com.example.universe.entity.OreEntity;
import com.example.universe.model.ObjectOre;
import com.example.universe.model.Ore;
import com.example.universe.model.PlanetSystem;
import com.example.universe.service.ObjectOreService;
import com.example.universe.service.OreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "ore")
public class OreController {
    private final OreService oreService;
    //private final ObjectOreService objectOreService;

    /*public OreController(OreService oreService) {
        this.oreService = oreService;
    }*/

    public OreController(OreService oreService) {
        this.oreService = oreService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<OreEntity>> getAllOre(){
        List<OreEntity> ore = oreService.findAllOre();
        return new ResponseEntity<>(ore, HttpStatus.OK);
    }

    /*@GetMapping("/find/{id}")
    public ResponseEntity<OreEntity> getOreById(@PathVariable("id")String id){
        OreEntity oreEntity = oreService.findOreById(UUID.fromString(id));
        return new ResponseEntity<>(oreEntity, HttpStatus.OK);
    }*/
    /*@GetMapping("/findobj")
    public ResponseEntity<List<ObjectOreEntity>> getOreObj(){
        List<ObjectOreEntity> ore = objectOreService.findAllOre();
        return new ResponseEntity<>(ore, HttpStatus.OK);
    }*/
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OreEntity> addOre(@RequestBody OreEntity ore){
        OreEntity newOre = oreService.addOre(ore);//OreEntity.toEntity(ore)
        return new ResponseEntity<>(newOre, HttpStatus.CREATED);
    }
    /*@PostMapping("/update")
    public ResponseEntity<OreEntity> updateOre(@RequestBody OreEntity ore){
        OreEntity updateOre = oreService.updateOre(ore);
        return new ResponseEntity<>(updateOre, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOre(@PathVariable("id") UUID id) throws IllegalAccessException {
        oreService.deleteOre(id);
    }*/
}
