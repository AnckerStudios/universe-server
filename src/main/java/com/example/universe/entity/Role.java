
package com.example.universe.entity;

import javax.persistence.*;


import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "t_role")
public class Role{
    @Id
    private UUID id;

    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(UUID id) {
        this.id = id;
    }

    public Role() {
    }
    public Role(String name) {
        this.name = name;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
