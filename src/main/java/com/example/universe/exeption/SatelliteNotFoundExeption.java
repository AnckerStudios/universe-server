package com.example.universe.exeption;

public class SatelliteNotFoundExeption extends RuntimeException {
    public SatelliteNotFoundExeption(String s) {
        super(s);
    }
}
