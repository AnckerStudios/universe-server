package com.example.universe.model;
import com.example.universe.entity.CreatureEntity;
import java.util.UUID;

public class Creature {
    private String id;
    private String name;

    public static Creature toModel (CreatureEntity enity){
        Creature creature = new Creature();
        creature.setId(enity.getId());
        creature.setName(enity.getName());
        return creature;
    }

    public Creature() {
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
