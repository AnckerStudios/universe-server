package com.example.universe.model;

import com.example.universe.entity.PlanetSystemEntity;
import com.example.universe.entity.SatelliteEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Satellite {
    private String id;
    private String name;
    private String discriminator;
    private Climate climate;
    private int radius;
    private PlanetSystem planetSystem;
    private Satellite satellite;
    private List<Satellite> satellites;
    private List<ObjectOre> objectOre;

    public List<Coords> getListSt() {
        return listSt;
    }

    public void setListSt(List<Coords> listSt) {
        this.listSt = listSt;
    }

    private List<Coords> listSt;
    private List<Creature> creatures;

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<Creature> creatures) {
        this.creatures = creatures;
    }

    public List<ObjectOre> getObjectOre() {
        return objectOre;
    }

    public void setObjectOre(List<ObjectOre> ores) {
        this.objectOre = ores;
    }

    public UUID getId() {
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

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    public Satellite() {
    }

    public PlanetSystem getPlanetSystem() {
        return planetSystem;
    }

    public void setPlanetSystem(PlanetSystem planetSystem) {
        this.planetSystem = planetSystem;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    public static Satellite toModel(SatelliteEntity entity){
        Satellite satellite = new Satellite();
        satellite.setId(entity.getId());
        satellite.setName(entity.getName());
        satellite.setDiscriminator(entity.getDiscriminator());
        satellite.setClimate(Climate.toModel(entity.getClimate()));
        satellite.setRadius(entity.getRadius());
        satellite.setSatellites(entity.getSatellites().stream().map(Satellite::toModel).collect(Collectors.toList()));
        satellite.setObjectOre(entity.getObjectOre().stream().map(ObjectOre::toModel).collect(Collectors.toList()));
        satellite.setCreatures(entity.getCreatures().stream().map(Creature::toModel).collect(Collectors.toList()));
        //satellite.setPlanetSystem(PlanetSystem.toModel(entity.getPlanetSystem()));
        return satellite;
    }
    public static Satellite toModelLow(SatelliteEntity entity){
        Satellite satellite = new Satellite();
        satellite.setId(entity.getId());
        satellite.setName(entity.getName());
        satellite.setDiscriminator(entity.getDiscriminator());
        satellite.setClimate(Climate.toModel(entity.getClimate()));
        satellite.setRadius(entity.getRadius());
        //satellite.setPlanetSystem(PlanetSystem.toModel(entity.getPlanetSystem()));
        return satellite;
    }
//    @Override
//    public String toString() {
//        return "Satellite{" +
//                "id='" + id + '\'' +
//                ", sat len =" + satellites.size() +
//                ", name='" + name + '\'' +
//                ", discriminator='" + discriminator + '\'' +
//                ", climate='" + climate + '\'' +
//                ", radius=" + radius +
//                ", planetSystem=" + planetSystem +
//                ", ores=" + objectOre +
//                ", satellites =" + satellites +
//                ", satellite =" + satellite +
//                ", creatures=" + creatures +
//                '}';
//    }
}
