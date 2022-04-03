package com.example.universe.model.request;

import java.util.HashSet;
import java.util.Set;

public class SignupRequest {
    private String username;
    private String password;

    public SignupRequest() {
    }

    public SignupRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        Set<String> s = new HashSet();
        s.add("ROLE_ADMIN");
        return s;
    }
}
