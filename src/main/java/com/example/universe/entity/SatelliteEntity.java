package com.example.universe.entity;

import com.example.universe.model.Creature;
import com.example.universe.model.Ore;
import com.example.universe.model.Satellite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "climate")
    private ClimateEntity climate;


    @Column(name = "radius")
    private int radius;

    public PlanetSystemEntity getPlanetSystem() {
        return planetSystem;
    }

    public void setPlanetSystem(PlanetSystemEntity planetSystem) {
        this.planetSystem = planetSystem;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.REFRESH})//fetch = FetchType.LAZY,cascade = CascadeType.ALL
    @JoinColumn(name = "fk_sys_uuid")
    private PlanetSystemEntity planetSystem;

    @OneToMany(mappedBy = "satellite", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true) //fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true
    private List<SatelliteEntity> satellites;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.REFRESH})//fetch = FetchType.LAZY,cascade = CascadeType.ALL
    @JoinColumn(name = "fk_obj_uuid")
    private SatelliteEntity satellite;

    @OneToMany(mappedBy = "satellite", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjectOreEntity> objectOre;

    @ManyToMany
    @JoinTable(name="object_creature",
            joinColumns=@JoinColumn (name="fk2_obj_uuid"),
            inverseJoinColumns=@JoinColumn(name="fk2_cre_uuid"))
    private List<CreatureEntity> creatures;

    public SatelliteEntity(UUID id, String name, String discriminator, ClimateEntity climate) {
        this.id = id;
        this.name = name;
        this.discriminator = discriminator;
        this.climate = climate;
    }
    public List<CreatureEntity> getCreatures() {
        return creatures;
    }

    public void setCreatures(List<CreatureEntity> creatures) {
        this.creatures = creatures;
    }

    public List<ObjectOreEntity> getObjectOre() {
        return objectOre;
    }

    public void setObjectOre(List<ObjectOreEntity> objectOre) {
        this.objectOre = objectOre;
    }

    public SatelliteEntity(UUID id) {
        this.id = id;
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

    public ClimateEntity getClimate() {
        return climate;
    }

    public void setClimate(ClimateEntity climate) {
        this.climate = climate;
    }

    public List<SatelliteEntity> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<SatelliteEntity> satellites) {
        this.satellites = satellites;
    }

    public SatelliteEntity getSatellite() {
        return satellite;
    }

    public void setSatellite(SatelliteEntity satellite) {
        this.satellite = satellite;
    }

    public static SatelliteEntity toEntity(Satellite model){
        System.out.println("satEnt = "+ model);
        SatelliteEntity satellite = new SatelliteEntity();
        satellite.setId(model.getId());
        satellite.setName(model.getName());
        satellite.setDiscriminator(model.getDiscriminator());
        satellite.setClimate(ClimateEntity.toEntity(model.getClimate()));
        satellite.setRadius(model.getRadius());
        satellite.setPlanetSystem(PlanetSystemEntity.toEntity(model.getPlanetSystem()));
        satellite.setObjectOre(model.getObjectOre().stream().map(ObjectOreEntity::toEntity).collect(Collectors.toList()));
        satellite.setSatellites(model.getSatellites().stream().map(SatelliteEntity::toEntityMoon).collect(Collectors.toList()));
        satellite.setCreatures(model.getCreatures().stream().map(CreatureEntity::toEntity).collect(Collectors.toList()));
        if(model.getSatellite() != null)
            satellite.setSatellite(SatelliteEntity.toEntityLow(model.getSatellite()));
        return satellite;
    }
    public static SatelliteEntity toEntityHigh(Satellite model){
        System.out.println("satEnt = "+ model);
        SatelliteEntity satellite = new SatelliteEntity();
        satellite.setId(model.getId());
        satellite.setName(model.getName());
        satellite.setDiscriminator(model.getDiscriminator());
        satellite.setClimate(ClimateEntity.toEntity(model.getClimate()));
        satellite.setRadius(model.getRadius());
        if(model.getPlanetSystem() != null)
            satellite.setPlanetSystem(PlanetSystemEntity.toEntityLow(model.getPlanetSystem()));
        satellite.setObjectOre(model.getObjectOre().stream().map(ObjectOreEntity::toEntityHigh).collect(Collectors.toList()));
        satellite.setSatellites(model.getSatellites().stream().map(SatelliteEntity::toEntityMoon).collect(Collectors.toList()));
        satellite.setCreatures(model.getCreatures().stream().map(CreatureEntity::toEntity).collect(Collectors.toList()));
        if(model.getSatellite() != null)
            satellite.setSatellite(SatelliteEntity.toEntityLow(model.getSatellite()));
        return satellite;
    }
    public static SatelliteEntity toEntityLow(Satellite model){
        System.out.println("satEnt = "+ model);
        SatelliteEntity satellite = new SatelliteEntity();
        satellite.setId(model.getId());
        satellite.setName(model.getName());
        satellite.setDiscriminator(model.getDiscriminator());
        satellite.setClimate(ClimateEntity.toEntity(model.getClimate()));
        satellite.setRadius(model.getRadius());
        satellite.setSatellites(model.getSatellites().stream().map(SatelliteEntity::toEntityMoon).collect(Collectors.toList()));
        if(model.getSatellite() != null)
            satellite.setSatellite(SatelliteEntity.toEntityLow(model.getSatellite()));
        //satellite.setPlanetSystem(PlanetSystemEntity.toEntityLow(model.getPlanetSystem()));
        return satellite;
    }

    public static SatelliteEntity toEntityMoon(Satellite model){
        System.out.println("satEnt = "+ model);
        SatelliteEntity satellite = new SatelliteEntity();
        satellite.setId(model.getId());
        satellite.setName(model.getName());
        satellite.setDiscriminator(model.getDiscriminator());
        satellite.setClimate(ClimateEntity.toEntity(model.getClimate()));
        satellite.setRadius(model.getRadius());
        satellite.setSatellites(model.getSatellites().stream().map(SatelliteEntity::toEntityLow).collect(Collectors.toList()));
        if(model.getSatellite() != null)
            satellite.setSatellite(SatelliteEntity.toEntityLow(model.getSatellite()));

        return satellite;
    }

    /*@Override
    public String toString() {
        return "SatelliteEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discriminator='" + discriminator + '\'' +
                ", climate='" + climate + '\'' +
                ", radius=" + radius +
                ", creatures=" + creatures +
                ", satellites=" + satellites +
                ", ps=" + (planetSystem != null ? planetSystem : "null")+
                ", satellite=" + (satellite != null ? satellite : "null")+
                '}';
    }*/
}

