package com.example.universe.controller;

import com.example.universe.entity.ClimateEntity;
import com.example.universe.entity.OreEntity;
import com.example.universe.service.ClimateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "climate")
public class ClimateController {
    private final ClimateService climateService;
    //private final ObjectOreService objectOreService;

    /*public OreController(OreService oreService) {
        this.oreService = oreService;
    }*/

    public ClimateController(ClimateService climateService) {
        this.climateService = climateService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ClimateEntity>> getAllClimate(){
        List<ClimateEntity> climate = climateService.findAllClimate();
        return new ResponseEntity<>(climate, HttpStatus.OK);
    }
}
