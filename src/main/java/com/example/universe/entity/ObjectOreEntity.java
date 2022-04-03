package com.example.universe.entity;

import com.example.universe.entity.keys.ObjectoreKey;
import com.example.universe.model.ObjectOre;
import com.example.universe.model.Ore;
import com.example.universe.model.Satellite;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "object_ore")
public class ObjectOreEntity {

    @EmbeddedId
    ObjectoreKey id;

    public ObjectOreEntity() {
    }
    public ObjectOreEntity(SatelliteEntity satellite, OreEntity ore, int value) {
        this.satellite = satellite;
        this.ore = ore;
        this.value = value;
    }

    public ObjectoreKey getId() {
        return id;
    }

    public void setId(ObjectoreKey id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public SatelliteEntity getSatellite() {
        return satellite;
    }

    public void setSatellite(SatelliteEntity satellite) {
        this.satellite = satellite;
    }

    public OreEntity getOre() {
        return ore;
    }

    public void setOre(OreEntity ore) {
        this.ore = ore;
    }


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @MapsId("objectId")
    @JoinColumn(name = "fk2_obj_uuid")
    private SatelliteEntity satellite;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @MapsId("oreId")
    @JoinColumn(name = "fk2_ore_uuid")
    private OreEntity ore;

    @Column(name = "ore_value")
    private int value;

    public static ObjectOreEntity toEntity(ObjectOre ore){
        ObjectOreEntity objectOre = new ObjectOreEntity();
        objectOre.setId(new ObjectoreKey(ore.getSatellite().getId(),ore.getOre().getId()));
        objectOre.setSatellite(SatelliteEntity.toEntity(ore.getSatellite()));
        objectOre.setOre(OreEntity.toEntity(ore.getOre()));
        objectOre.setValue(ore.getValue());
        return objectOre;
    }
    public static ObjectOreEntity toEntityLow(ObjectOre ore){
        ObjectOreEntity objectOre = new ObjectOreEntity();
        //objectOre.setId(new ObjectoreKey(ore.getSatellite().getId(),ore.getOre().getId()));
        //objectOre.setSatellite(SatelliteEntity.toEntity(ore.getSatellite()));
        objectOre.setOre(OreEntity.toEntity(ore.getOre()));
        objectOre.setValue(ore.getValue());
        return objectOre;
    }
    public static ObjectOreEntity toEntityHigh(ObjectOre ore){
        ObjectOreEntity objectOre = new ObjectOreEntity();
        objectOre.setId(new ObjectoreKey(ore.getSatellite().getId(),ore.getOre().getId()));
        objectOre.setSatellite(SatelliteEntity.toEntityLow(ore.getSatellite()));
        objectOre.setOre(OreEntity.toEntityLow(ore.getOre()));
        objectOre.setValue(ore.getValue());
        return objectOre;
    }

    @Override
    public String toString() {
        return "ObjectOreEntity{" +
                "id=" + id +
                ", satellite=" + satellite +
                ", ore=" + ore +
                ", value=" + value +
                '}';
    }
}
