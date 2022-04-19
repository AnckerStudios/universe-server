package com.example.universe.model;

import com.example.universe.entity.ClimateEntity;

public class Climate {
    private String climateName;
    private String color;


    public Climate() {
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

    public static Climate toModel(ClimateEntity entity){

        Climate climate = new Climate();
        climate.setClimateName(entity.getClimateName());
        climate.setColor(entity.getColor());
        return climate;
    }

    @Override
    public String toString() {
        return "Climate{" +
                "climateName='" + climateName + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
