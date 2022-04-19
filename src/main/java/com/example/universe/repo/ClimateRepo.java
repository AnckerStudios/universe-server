package com.example.universe.repo;

import com.example.universe.entity.ClimateEntity;
import com.example.universe.entity.OreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClimateRepo extends JpaRepository<ClimateEntity, String> {
}
