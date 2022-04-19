package com.example.universe.service;

import com.example.universe.entity.ClimateEntity;
import com.example.universe.entity.OreEntity;
import com.example.universe.repo.ClimateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClimateService {
    private final ClimateRepo climateRepo;
    @Autowired
    public ClimateService(ClimateRepo climateRepo) {
        this.climateRepo = climateRepo;
    }
    public List<ClimateEntity> findAllClimate() {
        return climateRepo.findAll();
    }
}
