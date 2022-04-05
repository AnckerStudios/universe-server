package com.example.universe.repo;
import com.example.universe.entity.PlanetSystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface PlanetSystemRepo extends JpaRepository<PlanetSystemEntity,UUID> {
    //void deletePlanetSystemByID(UUID id);

    Optional<PlanetSystemEntity> findPlanetSystemById(UUID id);

    List<PlanetSystemEntity> findPlanetSystemByName(String name);

    @Query(value = "SELECT * FROM planetSystem ps WHERE ps.xcoord > ?1 and ps.xcoord < ?2 and ps.ycoord > ?3 and ps.ycoord < ?4",nativeQuery = true)
    List<PlanetSystemEntity> findPlanetSystemByCoords(int coordX1, int coordX2, int coordY1, int coordY2);

    @Modifying
    @Query(value = "update planetSystem set xcoord = ?2, ycoord = ?3 where sys_uuid = ?1", nativeQuery=true)
    int updatePS(UUID uuid, int X, int Y);
}
