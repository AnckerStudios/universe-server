package com.example.universe.controller;

import com.example.universe.entity.CreatureEntity;
import com.example.universe.model.Creature;
import com.example.universe.service.CreatureService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "universe")
public class CreatureController {
    private final CreatureService creatureService;
    private static final Logger logger = LogManager.getLogger();

    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Creature>> getAllCreature(){
        List<Creature> creature = creatureService.findAllCreature();
        logger.debug("Creatures list founded successfully");
        return new ResponseEntity<>(creature, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<CreatureEntity> getCreatureById(@PathVariable("id")String id){
        CreatureEntity creature = creatureService.findCreatureById(UUID.fromString(id));
        logger.debug("Creature by " + id + " founded successfully");
        return new ResponseEntity<>(creature, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<CreatureEntity> addCreature(@RequestBody CreatureEntity creature){
        CreatureEntity newCreature = creatureService.addCreature(creature);
        logger.debug("Creature " + creature.getName() + " added successfully");
        return new ResponseEntity<>(newCreature, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<CreatureEntity> updateCreature(@RequestBody CreatureEntity creature){
        CreatureEntity updateCreature = creatureService.updateCreature(creature);
        logger.debug("Creature " + creature.getName() + " updated successfully");
        return new ResponseEntity<>(updateCreature, HttpStatus.OK);
    }
    @DeleteMapping(path = "{id}")
    public void deleteCreature(@PathVariable("id") UUID id) throws IllegalAccessException {
        try {
            logger.debug("Creature deleted successfully");
            creatureService.deleteCreature(id);
        } catch (Exception e){
            logger.error("Error!" + e);
            throw new IllegalAccessException("Error! Cant delete creature with id " + id);
        }
    }
}
