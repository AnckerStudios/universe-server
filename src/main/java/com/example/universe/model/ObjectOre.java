package com.example.universe.model;

import com.example.universe.entity.ObjectOreEntity;

public class ObjectOre {
    private Satellite satellite;
    private Ore ore;
    private int value;

    public ObjectOre() {
    }

    public ObjectOre(Ore ore, int value) {
        this.ore = ore;
        this.value = value;
    }
    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public Ore getOre() {
        return ore;
    }

    public void setOre(Ore ore) {
        this.ore = ore;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static ObjectOre toModel(ObjectOreEntity entity){
        ObjectOre objectOre = new ObjectOre();
        objectOre.setSatellite(Satellite.toModelLow(entity.getSatellite()));
        objectOre.setOre(Ore.toModel(entity.getOre()));
        objectOre.setValue(entity.getValue());
        return objectOre;
    }

//    @Override
//    public String toString() {
//        return "ObjectOre{" +
//                "satellite=" + satellite +
//                ", ore=" + ore +
//                ", value=" + value +
//                '}';
//    }
}
