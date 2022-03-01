package com.example.universe.controller;

import com.example.universe.entity.CreatureEntity;
import com.example.universe.model.Creature;
import com.example.universe.service.CreatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "universe")
public class CreatureController {
    private final CreatureService creatureService;

    public CreatureController(CreatureService creatureService) {
        this.creatureService = creatureService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Creature>> getAllCreature(){
        List<Creature> creature = creatureService.findAllCreature();
        return new ResponseEntity<>(creature, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<CreatureEntity> getCreatureById(@PathVariable("id")String id){
        System.out.println(id);
        System.out.println(UUID.fromString(id));
        CreatureEntity creature = creatureService.findCreatureById(UUID.fromString(id));
        return new ResponseEntity<>(creature, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<CreatureEntity> addCreature(@RequestBody CreatureEntity creature){
        CreatureEntity newCreature = creatureService.addCreature(creature);
        return new ResponseEntity<>(newCreature, HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<CreatureEntity> updateCreature(@RequestBody CreatureEntity creature){
        CreatureEntity updateCreature = creatureService.updateCreature(creature);
        return new ResponseEntity<>(updateCreature, HttpStatus.OK);
    }
    @DeleteMapping(path = "{id}")
    public void deleteCreature(@PathVariable("id") UUID id) throws IllegalAccessException {
        creatureService.deleteCreature(id);
    }
}
