package com.example.universe.model.responses;

import java.util.List;
import java.util.UUID;

public class JwtResponse {
    private String token;
    private UUID id;
    private String username;
    private List<String> role;

    public JwtResponse() {
    }

    public JwtResponse(String token, UUID id, String username, List<String> role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
