package com.example.universe.repo;
import com.example.universe.entity.OreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface OreRepo extends JpaRepository <OreEntity,UUID>{
    Optional<OreEntity> findOreById(UUID id);
}
