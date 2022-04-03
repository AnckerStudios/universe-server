package com.example.universe.model;
import com.example.universe.entity.ObjectOreEntity;
import com.example.universe.entity.OreEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Ore {
    private String id;
    private String name;
    private List<ObjectOre> objectOres;

    public static Ore toModel (OreEntity enity){
        Ore ore = new Ore();
        ore.setId(enity.getId());
        ore.setName(enity.getName());

        //ore.setObjectOres(enity.getObjectOre().stream().map(ObjectOre::toModel).collect(Collectors.toList()));
        return ore;
    }
    public static Ore toModel (ObjectOreEntity enity){
        Ore ore = new Ore();
        ore.setId(enity.getOre().getId());
        ore.setName(enity.getOre().getName());
        //ore.setValue(enity.getValue());
        //ore.setObjectOres(enity);
        return ore;
    }

    public Ore(){
    }

    public Ore(String id, String name, List<ObjectOre> objectOres) {
        this.id = id;
        this.name = name;
        this.objectOres = objectOres;
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
   /* public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }*/
    public List<ObjectOre> getObjectOres() {
        return objectOres;
    }
    public void setObjectOres(List<ObjectOre> objectOres) {
        this.objectOres = objectOres;
    }

    @Override
    public String toString() {
        return "Ore{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
