package com.example.universe.model;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PlanetSystem {
    private String id;
    private String name;
    private List<Satellite> satellites;
    private Integer YCoord;
    private Integer XCoord;

    public static PlanetSystem toModel(PlanetSystemEntity entity){
        PlanetSystem planetSystem = new PlanetSystem();
        planetSystem.setId(entity.getId());
        planetSystem.setName(entity.getName());
        planetSystem.setSatellites(entity.getSatellites().stream().map(Satellite::toModel).collect(Collectors.toList()));
        planetSystem.setXCoord(entity.getXCoord());
        planetSystem.setYCoord(entity.getYCoord());
        return planetSystem;
    }

    public UUID getId() {
        System.out.println("id = "+ this.id +"or "+UUID.fromString(this.id));
        return UUID.fromString(this.id);
    }

    public void setId(UUID id) {
        this.id = id.toString();
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

    public int getXCoord() {
        return XCoord;
    }

    public void setXCoord(int xCoord) {
        this.XCoord = xCoord;
    }

    public Integer getYCoord() {
        return YCoord;
    }

    public void setYCoord(Integer YCoord) {
        this.YCoord = YCoord;
    }
}
