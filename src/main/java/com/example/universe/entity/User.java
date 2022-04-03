
package com.example.universe.entity;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "t_user")
public class User {
    @Id
    @Column(name = "id")//@Type(type = TypeAnnotation.UUID_CHAR_TYPE)
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "fk_user_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_role_id"))
    private Set<Role> roles;

    public User() {
        this.id = UUID.randomUUID();
    }

/*    public User() {
    }*/
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
