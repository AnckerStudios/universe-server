package com.example.universe.entity;

import com.example.universe.model.Creature;
import com.example.universe.model.Ore;
import com.example.universe.model.Satellite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "spaceobject")
public class SatelliteEntity implements Serializable {
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

    public PlanetSystemEntity getPlanetSystem() {
        return planetSystem;
    }

    public void setPlanetSystem(PlanetSystemEntity planetSystem) {
        this.planetSystem = planetSystem;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)//fetch = FetchType.LAZY,cascade = CascadeType.ALL
    @JoinColumn(name = "fk_sys_uuid")
    private PlanetSystemEntity planetSystem;

    public List<OreEntity> getOres() {
        return ores;
    }

    public void setOres(List<OreEntity> ores) {
        this.ores = ores;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public static SatelliteEntity toEntity(Satellite model){
        SatelliteEntity satellite = new SatelliteEntity();
        satellite.setId(model.getId());
        satellite.setName(model.getName());
        satellite.setDiscriminator(model.getDiscriminator());
        satellite.setClimate(model.getClimate());
        satellite.setRadius(model.getRadius());
        satellite.setOres(model.getOres().stream().map(OreEntity::toEntity).collect(Collectors.toList()));
        satellite.setCreatures(model.getCreatures().stream().map(CreatureEntity::toEntity).collect(Collectors.toList()));
        return satellite;
    }
}

