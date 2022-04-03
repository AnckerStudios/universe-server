package com.example.universe.service;

import com.example.universe.entity.ObjectOreEntity;
import com.example.universe.entity.OreEntity;
import com.example.universe.entity.keys.ObjectoreKey;
import com.example.universe.exeption.OreNotFoundExeption;
import com.example.universe.repo.ObjectOreRepo;
import com.example.universe.repo.OreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ObjectOreService {
    private final ObjectOreRepo OreRepo;

    @Autowired
    public ObjectOreService(ObjectOreRepo OreRepo) {
        this.OreRepo = OreRepo;
    }

    public List<ObjectOreEntity> findAllOre() {
        return OreRepo.findAll();//.stream().map(s -> Ore.toModel(s.getObjectOre())).collect(Collectors.toList());
    }
    public ObjectOreEntity addOre(ObjectOreEntity ore) {
        return OreRepo.save(ore);
    }
    public void deleteObjOre(ObjectoreKey id){
        OreRepo.deleteById(id);
    }
}
