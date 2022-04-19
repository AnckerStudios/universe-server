package com.example.universe.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SatelliteNotFoundExeption extends RuntimeException {
    public SatelliteNotFoundExeption(String s) {
        super(s);
    }
}
