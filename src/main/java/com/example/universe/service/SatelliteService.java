package com.example.universe.service;

import com.example.universe.exeption.SatelliteNotFoundExeption;
import com.example.universe.entity.SatelliteEntity;
import com.example.universe.model.Satellite;
import com.example.universe.repo.ObjectOreRepo;
import com.example.universe.repo.SatelliteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SatelliteService {
    private final SatelliteRepo satelliteRepo;
    private final ObjectOreRepo oreRepo;
    @Autowired
    public SatelliteService(SatelliteRepo satelliteRepo, ObjectOreRepo oreRepo){
        this.satelliteRepo = satelliteRepo;
        this.oreRepo = oreRepo;
    }
    public SatelliteEntity addSatellite(SatelliteEntity satellite){
        //satellite.setId(UUID.randomUUID());
        //for(SatelliteEntity s : satellite.getSatellites())
        //    s.setSatellite(satellite);
        return satelliteRepo.save(satellite);
    }
    public List<Satellite> findAllSatellite(){
        return satelliteRepo.findAll().stream().map(s -> Satellite.toModel(s)).collect(Collectors.toList());
    }
    public SatelliteEntity updateSatellite(SatelliteEntity satellite){
        return satelliteRepo.save(satellite);
    }
    public Satellite findSatelliteById(UUID id){
        SatelliteEntity s = satelliteRepo.findSatelliteById(id).orElseThrow(() -> new SatelliteNotFoundExeption("Satellite by id "+id+"was not found"));
        System.out.println(s);
        return Satellite.toModel(s);
    }
    public List<Satellite> findSatelliteByName(String name){
        List<SatelliteEntity> s = satelliteRepo.findSatelliteByName(name);
        System.out.println(s);
        return s.stream().map(Satellite::toModel).collect(Collectors.toList());
    }
    public void deleteSatellite(UUID id){

        //oreRepo.delete();
        satelliteRepo.deleteById(id);
    }

    /*public List<PlanetSystem> updateCoord(int coordX, int coordY) {
        return satelliteRepo.findPlanetSystemByCoords(coordX-range,coordX+range,coordY-range,coordY+range).stream().map(s -> PlanetSystem.toModel(s)).collect(Collectors.toList());
    }*/
}
