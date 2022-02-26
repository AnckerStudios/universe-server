package com.example.universe.model;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Satellite {
    private UUID id;
    private String name;
    private String discriminator;
    private String climate;
    private int radius;
    private List<Ore> ores;
    private List<Creature> creatures;

    public static Satellite toModel(SatelliteEntity entity){
        Satellite satellite = new Satellite();
        satellite.setId(entity.getId());
        satellite.setName(entity.getName());
        satellite.setDiscriminator(entity.getDiscriminator());
        satellite.setClimate(entity.getClimate());
        satellite.setRadius(entity.getRadius());
        satellite.setOres(entity.getOres().stream().map(Ore::toModel).collect(Collectors.toList()));
        satellite.setCreatures(entity.getCreatures().stream().map(Creature::toModel).collect(Collectors.toList()));
        return satellite;
    }
    public List<Creature> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<Creature> creatures) {
        this.creatures = creatures;
    }

    public List<Ore> getOres() {
        return ores;
    }

    public void setOres(List<Ore> ores) {
        this.ores = ores;
    }

    public UUID getId() {
        return id;
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

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public Satellite() {
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
