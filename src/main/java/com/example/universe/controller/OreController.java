package com.example.universe.controller;

import com.example.universe.entity.OreEntity;
import com.example.universe.model.Ore;
import com.example.universe.service.OreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "universe")
public class OreController {
    private final OreService oreService;

    public OreController(OreService oreService) {
        this.oreService = oreService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ore>> getAllOre(){
        List<Ore> ore = oreService.findAllOre();
        return new ResponseEntity<>(ore, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<OreEntity> getOreById(@PathVariable("id")String id){
        System.out.println(id);
        System.out.println(UUID.fromString(id));
        OreEntity oreEntity = oreService.findOreById(UUID.fromString(id));
        return new ResponseEntity<>(oreEntity, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<OreEntity> addOre(@RequestBody OreEntity ore){
        OreEntity newOre = oreService.addOre(ore);
        return new ResponseEntity<>(newOre, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<OreEntity> updateOre(@RequestBody OreEntity ore){
        OreEntity updateOre = oreService.updateOre(ore);
        return new ResponseEntity<>(updateOre, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOre(@PathVariable("id") UUID id) throws IllegalAccessException {
        oreService.deleteOre(id);
    }
}
