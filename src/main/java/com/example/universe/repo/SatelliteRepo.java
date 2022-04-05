package com.example.universe.repo;
import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SatelliteRepo extends JpaRepository<SatelliteEntity, UUID> {
    void deleteById(UUID id);
    Optional<SatelliteEntity> findSatelliteById(UUID id);
    List<SatelliteEntity> findSatelliteByName(String name);
}
