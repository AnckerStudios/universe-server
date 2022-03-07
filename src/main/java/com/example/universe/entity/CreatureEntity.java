package com.example.universe.entity;

import com.example.universe.model.Creature;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creature")
public class CreatureEntity {
    @Id
    @Column(name = "cre_uuid")
    private UUID id;
    @Column(name = "cre_name")
    private String name;

    @ManyToMany
    @JoinTable(name = "object_creature",
            joinColumns = @JoinColumn(name = "fk2_cre_uuid"),
            inverseJoinColumns = @JoinColumn(name = "fk2_obj_uuid"))
    private List<SatelliteEntity> satellites;

    public CreatureEntity(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public CreatureEntity() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static CreatureEntity toEntity (Creature model){
        CreatureEntity creature = new CreatureEntity();
        creature.setId(model.getId());
        creature.setName(model.getName());
        return creature;
    }
}

