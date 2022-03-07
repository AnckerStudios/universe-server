package com.example.universe.service;

import com.example.universe.exeption.OreNotFoundExeption;
import com.example.universe.entity.OreEntity;
import com.example.universe.model.Ore;
import com.example.universe.repo.OreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OreService {
    private final OreRepo OreRepo;

    @Autowired
    public OreService(OreRepo OreRepo) {
        this.OreRepo = OreRepo;
    }

    public OreEntity addOre(OreEntity ore) {
        ore.setId(UUID.randomUUID());
        return OreRepo.save(ore);
    }
    public List<Ore> findAllOre() {
        return OreRepo.findAll().stream().map(s -> Ore.toModel(s)).collect(Collectors.toList());
    }
    public OreEntity updateOre(OreEntity ore) {
        return OreRepo.save(ore);
    }

    public OreEntity findOreById(UUID id) {
        return OreRepo.findOreById(id).orElseThrow(() -> new OreNotFoundExeption("Ore by id" + id + "was not found"));
    }
    public void deleteOre(UUID id) throws IllegalAccessException {
        boolean exists = OreRepo.existsById(id);
        if(!exists){
            throw new IllegalAccessException("ore with id " + id + " does not exists");
        }
        OreRepo.deleteById(id);
    }
}
