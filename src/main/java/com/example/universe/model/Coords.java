package com.example.universe.model;

public class Coords {
    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Coords(int coordX, int coordY, int range) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.range = range;
    }

    private int coordX;
    private int coordY;
    private int range;

    @Override
    public String toString() {
        return "Coords{" +
                "coordX=" + coordX +
                ", coordY=" + coordY +
                ", range=" + range +
                '}';
    }
}
