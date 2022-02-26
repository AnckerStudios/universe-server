package com.example.universe.repo;
import com.example.universe.entity.SatelliteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SatelliteRepo extends JpaRepository<SatelliteEntity, UUID> {
    Optional<SatelliteEntity> findSatelliteById(UUID id);
}
