package com.example.universe.entity;

import com.example.universe.model.PlanetSystem;
import com.example.universe.model.Satellite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "planetsystem")
public class PlanetSystemEntity implements Serializable {
    @Id
    @Column(name = "sys_uuid")
    private UUID id;
    @Column(name = "sys_name")
    private String name;

    @Column(name = "ycoord")
    private Integer YCoord;
    @Column(name = "xcoord")
    private Integer XCoord;
    @OneToMany(mappedBy = "planetSystem", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true) //fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true
    private List<SatelliteEntity> satellites;

    public int getXCoord() {
        return XCoord;
    }

    public void setXCoord(int xCoord) {
        this.XCoord = xCoord;
    }

    public void setSatellites(List<SatelliteEntity> satellites) {
        this.satellites = satellites;
    }

    public List<SatelliteEntity> getSatellites() {
        return satellites;
    }

    public PlanetSystemEntity() {
    }

    public PlanetSystemEntity( String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
    public PlanetSystemEntity( PlanetSystem ps) {
        this.id = ps.getId();
        this.name = ps.getName();
        //this.satellites = ps.getSatellites();

    }

    public void setId(UUID id){
        this.id = id;
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

    public Integer getYCoord() {
        return YCoord;
    }

    public void setYCoord(Integer YCoord) {
        this.YCoord = YCoord;
    }
    public static PlanetSystemEntity toEntity(PlanetSystem model){
        System.out.println("model = "+model);
        PlanetSystemEntity planetSystem = new PlanetSystemEntity();
        planetSystem.setId(model.getId());
        planetSystem.setName(model.getName());
        planetSystem.setSatellites(model.getSatellites().stream().map(SatelliteEntity::toEntity).collect(Collectors.toList()));
        planetSystem.setXCoord(model.getXCoord());
        planetSystem.setYCoord(model.getYCoord());
        return planetSystem;
    }
    public static PlanetSystemEntity toEntityLow(PlanetSystem model){
        System.out.println("model = "+model);
        PlanetSystemEntity planetSystem = new PlanetSystemEntity();
        planetSystem.setId(model.getId());
        planetSystem.setName(model.getName());
        //planetSystem.setSatellites(model.getSatellites().stream().map(SatelliteEntity::toEntity).collect(Collectors.toList()));
        planetSystem.setXCoord(model.getXCoord());
        planetSystem.setYCoord(model.getYCoord());
        return planetSystem;
    }
    public static PlanetSystemEntity toEntityHigh(PlanetSystem model){
        System.out.println("model = "+model);
        PlanetSystemEntity planetSystem = new PlanetSystemEntity();
        planetSystem.setId(model.getId());
        planetSystem.setName(model.getName());
        planetSystem.setSatellites(model.getSatellites().stream().map(SatelliteEntity::toEntityHigh).collect(Collectors.toList()));
        planetSystem.setXCoord(model.getXCoord());
        planetSystem.setYCoord(model.getYCoord());
        return planetSystem;
    }

    /*@Override
    public String toString() {
        return "PlanetSystemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", YCoord=" + YCoord +
                ", XCoord=" + XCoord +
                ", satellites=" + satellites +
                '}';
    }*/
}
