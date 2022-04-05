package com.example.universe.service;

import com.example.universe.entity.ObjectOreEntity;
import com.example.universe.entity.keys.ObjectoreKey;
import com.example.universe.model.Satellite;
import com.example.universe.repo.ObjectOreRepo;
import com.example.universe.repo.SatelliteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ObjectOreService {
    private final ObjectOreRepo oreRepo;
    private final SatelliteRepo satelliteRepo;

    @Autowired
    public ObjectOreService(ObjectOreRepo oreRepo, SatelliteRepo satelliteRepo) {
        this.oreRepo = oreRepo;
        this.satelliteRepo = satelliteRepo;
    }

    public List<ObjectOreEntity> findAllOre() {
        return oreRepo.findAll();//.stream().map(s -> Ore.toModel(s.getObjectOre())).collect(Collectors.toList());
    }
    public List<Satellite> findSatelliteByOre(String ore){
        System.out.println("пришло 2 "+ore);
        List<String> satId = oreRepo.getSatelliteByOre(ore);
        List<Satellite> sat = new ArrayList<>();
        for(String s : satId){
            sat.add(Satellite.toModel(satelliteRepo.getById(UUID.fromString(s))));
        }
        return sat;//s.stream().map(Satellite::toModel).collect(Collectors.toList());
    }
    public ObjectOreEntity addOre(ObjectOreEntity ore) {
        return oreRepo.save(ore);
    }
    public void deleteObjOre(ObjectoreKey id){
        oreRepo.deleteById(id);
    }
}
