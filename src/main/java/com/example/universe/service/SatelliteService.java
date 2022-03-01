package com.example.universe.service;

import com.example.universe.entity.SatelliteEntity;
import com.example.universe.exeption.SatelliteNotFoundExeption;
import com.example.universe.model.Satellite;
import com.example.universe.repo.SatelliteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SatelliteService {
    private final SatelliteRepo satelliteRepo;
    @Autowired
    public SatelliteService(SatelliteRepo satelliteRepo){
        this.satelliteRepo = satelliteRepo;
    }
    public SatelliteEntity addSatellite(SatelliteEntity satellite){
        satellite.setId(UUID.randomUUID());
        return satelliteRepo.save(satellite);
    }
    public List<Satellite> findAllSatellite(){
        return satelliteRepo.findAll().stream().map(s -> Satellite.toModel(s)).collect(Collectors.toList());
    }
    public SatelliteEntity updateSatellite(SatelliteEntity satellite){
        return satelliteRepo.save(satellite);
    }
    public SatelliteEntity findSatelliteById(UUID id){
        return satelliteRepo.findSatelliteById(id).orElseThrow(() -> new SatelliteNotFoundExeption("Satellite by id"+id+"was not found"));
    }
}
