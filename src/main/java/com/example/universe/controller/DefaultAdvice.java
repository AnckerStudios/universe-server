package com.example.universe.controller;

import com.example.universe.exeption.CreatureNotFoundExeption;
import com.example.universe.exeption.OreNotFoundExeption;
import com.example.universe.exeption.PlanetSystemNotFoundExeption;
import com.example.universe.exeption.SatelliteNotFoundExeption;
import com.example.universe.model.responses.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler(SatelliteNotFoundExeption.class)
    public ResponseEntity<MessageResponse> satelliteNotFound(SatelliteNotFoundExeption e) {
        MessageResponse response = new MessageResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(PlanetSystemNotFoundExeption.class)
    public ResponseEntity<MessageResponse> planetSystemNotFound(PlanetSystemNotFoundExeption e) {
        MessageResponse response = new MessageResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(OreNotFoundExeption.class)
    public ResponseEntity<MessageResponse> oreNotFound(OreNotFoundExeption e) {
        MessageResponse response = new MessageResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CreatureNotFoundExeption.class)
    public ResponseEntity<MessageResponse> handleException(CreatureNotFoundExeption e) {
        MessageResponse response = new MessageResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
