package com.example.universe.controller;

import com.example.universe.entity.ObjectOreEntity;
import com.example.universe.entity.OreEntity;
import com.example.universe.entity.keys.ObjectoreKey;
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
@RequestMapping(path = "/objore")
public class ObjectOreController {
    private final ObjectOreService oreService;

    public ObjectOreController(ObjectOreService oreService) {
        this.oreService = oreService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ObjectOreEntity>> getAllOre(){
        List<ObjectOreEntity> ore = oreService.findAllOre();
        return new ResponseEntity<>(ore, HttpStatus.OK);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ObjectOreEntity> addOre(@RequestBody ObjectOre ore){
        ObjectOreEntity newOre = oreService.addOre(ObjectOreEntity.toEntityHigh(ore));//OreEntity.toEntity(ore)
        return new ResponseEntity<>(newOre, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteObjOre(@PathVariable("id") ObjectoreKey id){
        oreService.deleteObjOre(id);
    }
}