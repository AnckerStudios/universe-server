package com.example.universe.entity;

import com.example.universe.model.Climate;
import com.example.universe.model.Satellite;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "climate")
public class ClimateEntity {
    @Id
    @Column(name = "climate_name")
    private String climateName;
    @Column(name = "color")
    private String color;
    @OneToMany(mappedBy = "climate", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true) //cascade = CascadeType.ALL cascade = {CascadeType.PERSIST, CascadeType.REFRESH}
    private List<SatelliteEntity> satelliteEntities;
    public ClimateEntity(String climateName, String color) {
        this.climateName = climateName;
        this.color = color;
    }

    public ClimateEntity() {
    }

    public String getClimateName() {
        return climateName;
    }

    public void setClimateName(String climateName) {
        this.climateName = climateName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static ClimateEntity toEntity(Climate model){

        ClimateEntity climate = new ClimateEntity();
        climate.setClimateName(model.getClimateName());
        climate.setColor(model.getColor());
        return climate;
    }
}
