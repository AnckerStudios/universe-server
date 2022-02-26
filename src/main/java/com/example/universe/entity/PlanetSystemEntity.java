package com.example.universe.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "planetsystem")
public class PlanetSystemEntity implements Serializable {
    @Id
    @Column(name = "sys_uuid")
    private UUID id;
    @Column(name = "sys_name")
    private String name;
    @Column(name = "coords")
    private String coords;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planetSystem")
    private List<SatelliteEntity> satellites;

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
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

    public void setId(UUID Id){
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
}
