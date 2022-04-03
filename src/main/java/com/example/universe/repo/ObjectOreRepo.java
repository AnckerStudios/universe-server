package com.example.universe.repo;
import com.example.universe.entity.ObjectOreEntity;
import com.example.universe.entity.OreEntity;
import com.example.universe.entity.keys.ObjectoreKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ObjectOreRepo extends JpaRepository <ObjectOreEntity, ObjectoreKey>{
    Optional<ObjectOreEntity> findOreById(UUID id);
}
