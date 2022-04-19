package com.example.universe.controller;

import com.example.universe.entity.OreEntity;
import com.example.universe.model.Ore;
import com.example.universe.service.OreService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "universe")
public class OreController {
    private final OreService oreService;
    private static final Logger logger = LogManager.getLogger();

    public OreController(OreService oreService) {
        this.oreService = oreService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Ore>> getAllOre(){
        List<Ore> ore = oreService.findAllOre();
        logger.debug("Ore list founded successfully");
        return new ResponseEntity<>(ore, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<OreEntity> getOreById(@PathVariable("id")String id){
        OreEntity oreEntity = oreService.findOreById(UUID.fromString(id));
        logger.debug("Ore by " + id + " founded successfully");
        return new ResponseEntity<>(oreEntity, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<OreEntity> addOre(@RequestBody OreEntity ore){
        OreEntity newOre = oreService.addOre(ore);
        logger.debug("Ore " + ore.getName() + " updated successfully");
        return new ResponseEntity<>(newOre, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<OreEntity> updateOre(@RequestBody OreEntity ore){
        OreEntity updateOre = oreService.updateOre(ore);
        logger.debug("Ore " + ore.getName() + " updated successfully");
        return new ResponseEntity<>(updateOre, HttpStatus.OK);
    }
    @DeleteMapping(path = "{id}")
    public void deleteOre(@PathVariable("id") UUID id) throws IllegalAccessException {
        try {
            logger.debug("Ore deleted successfully");
            oreService.deleteOre(id);
        }catch (Exception e) {
            logger.error("Error!" + e);
            throw new IllegalAccessException("Error! Cant delete ore with id " + id);
        }

    }
}
