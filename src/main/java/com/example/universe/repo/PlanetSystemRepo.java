package com.example.universe.repo;
import com.example.universe.entity.PlanetSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PlanetSystemRepo extends JpaRepository<PlanetSystemEntity,UUID> {
    //void deletePlanetSystemByID(UUID id);

    Optional<PlanetSystemEntity> findPlanetSystemById(UUID id);
}
