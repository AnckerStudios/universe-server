package com.example.universe.model;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlanetSystem {
    private UUID id;
    private String name;
    private List<Satellite> satellites;
    private String coords;

    public static PlanetSystem toModel(PlanetSystemEntity entity){
        PlanetSystem planetSystem = new PlanetSystem();
        planetSystem.setId(entity.getId());
        planetSystem.setName(entity.getName());
        planetSystem.setSatellites(entity.getSatellites().stream().map(Satellite::toModel).collect(Collectors.toList()));
        planetSystem.setCoords(entity.getCoords());
        return planetSystem;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public PlanetSystem() {
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }
}
