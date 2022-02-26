package com.example.universe.entity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "spaceobject")
public class SatelliteEntity {
    @Id
    @Column(name = "obj_uuid")
    private UUID id;
    @Column(name = "obj_name")
    private String name;
    @Column(name = "discriminator")
    private String discriminator;
    @Column(name = "climate")
    private String climate;
    @Column(name = "fk_obj_uuid")
    private UUID fk_obj_uuid;

    @Column(name = "radius")
    private int radius;
    @ManyToOne
    @JoinColumn(name = "fk_sys_uuid")
    private PlanetSystemEntity planetSystem;

    public List<OreEntity> getOres() {
        return ores;
    }

    public void setOres(List<OreEntity> ores) {
        this.ores = ores;
    }

    @ManyToMany
    @JoinTable(name="object_ore",
            joinColumns=@JoinColumn (name="fk2_obj_uuid"),
            inverseJoinColumns=@JoinColumn(name="fk2_ore_uuid"))
    private List<OreEntity> ores;

    public List<CreatureEntity> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<CreatureEntity> creatures) {
        this.creatures = creatures;
    }

    @ManyToMany
    @JoinTable(name="object_creature",
            joinColumns=@JoinColumn (name="fk2_obj_uuid"),
            inverseJoinColumns=@JoinColumn(name="fk2_cre_uuid"))
    private List<CreatureEntity> creatures;

    public SatelliteEntity(UUID id, String name, String discriminator, String climate, UUID fk_obj_uuid) {
        this.id = id;
        this.name = name;
        this.discriminator = discriminator;
        this.climate = climate;
        this.fk_obj_uuid = fk_obj_uuid;
    }

    public SatelliteEntity() {
    }
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id){
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

    public UUID getFk_obj_uuid() {
        return fk_obj_uuid;
    }

    public void setFk_obj_uuid(UUID fk_obj_uuid) {
        this.fk_obj_uuid = fk_obj_uuid;
    }
}

