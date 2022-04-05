package com.example.universe.repo;
import com.example.universe.entity.ObjectOreEntity;
import com.example.universe.entity.OreEntity;
import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;
import com.example.universe.entity.keys.ObjectoreKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ObjectOreRepo extends JpaRepository<ObjectOreEntity, ObjectoreKey>{
    Optional<ObjectOreEntity> findOreById(UUID id);

    @Query(value = "SELECT obj_uuid FROM spaceobject obj WHERE obj.obj_uuid IN (SELECT fk2_obj_uuid FROM object_ore WHERE fk2_ore_uuid IN (SELECT ore_uuid FROM ore WHERE ore_name=?1))\n",nativeQuery = true)
    List<String> getSatelliteByOre(String ore);
}
