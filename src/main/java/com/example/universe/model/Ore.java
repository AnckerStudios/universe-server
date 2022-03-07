package com.example.universe.model;
import com.example.universe.entity.OreEntity;

import java.util.UUID;
public class Ore {
    private String id;
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
}
