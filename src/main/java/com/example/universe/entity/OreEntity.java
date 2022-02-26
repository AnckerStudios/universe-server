package com.example.universe.entity;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "ore")
public class OreEntity {
    @Id
    @Column(name = "ore_uuid")
    private UUID id;
    @Column(name = "ore_name")
    private String name;

    @ManyToMany
    @JoinTable(name="object_ore",
            joinColumns=@JoinColumn (name="fk2_ore_uuid"),
            inverseJoinColumns=@JoinColumn(name="fk2_obj_uuid"))
    private List<SatelliteEntity> satellites;

    public OreEntity(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
    public OreEntity() {}
    public void setId(UUID id){this.id=id;}
    public UUID getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
