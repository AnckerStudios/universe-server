package com.example.universe.model;
import com.example.universe.entity.OreEntity;

import java.util.UUID;
public class Ore {
    private UUID id;
    private String name;

    public static Ore toModel (OreEntity enity){
        Ore ore = new Ore();
        ore.setId(enity.getId());
        ore.setName(enity.getName());
        return ore;
    }

    public Ore(){
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
}
