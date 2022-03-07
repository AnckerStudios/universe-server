package com.example.universe.repo;
import com.example.universe.entity.CreatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CreatureRepo extends JpaRepository <CreatureEntity,UUID>{
    Optional<CreatureEntity> findCreatureById(UUID id);
}