package com.example.universe.entity;
import com.example.universe.entity.keys.ObjectoreKey;
import com.example.universe.model.ObjectOre;
import com.example.universe.model.Ore;
import com.example.universe.model.Satellite;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "ore")
public class OreEntity {
    @Id
    @Column(name = "ore_uuid")
    private UUID id;
    @Column(name = "ore_name")
    private String name;

    /*@ManyToMany
    @JoinTable(name="object_ore",
            joinColumns=@JoinColumn (name="fk2_ore_uuid"),
            inverseJoinColumns=@JoinColumn(name="fk2_obj_uuid"))
    private List<SatelliteEntity> satellites;*/

    public List<ObjectOreEntity> getObjectOre() {
        return objectOre;
    }

    public void setObjectOre(List<ObjectOreEntity> objectOre) {
        this.objectOre = objectOre;
    }

    @OneToMany(mappedBy = "ore", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true) //cascade = CascadeType.ALL cascade = {CascadeType.PERSIST, CascadeType.REFRESH}
    private List<ObjectOreEntity> objectOre;

    public OreEntity(Ore ore, ObjectOre objectOre) {
        this.id = ore.getId();
        this.name = ore.getName();
        List l = new ArrayList();
        ObjectOreEntity o = ObjectOreEntity.toEntityHigh(objectOre);
        System.out.println("o = "+o.toString());
        l.add(o);
        this.objectOre = l;
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

    public static OreEntity toEntity (Ore model){
        OreEntity ore = new OreEntity();
        ore.setId(model.getId());
        ore.setName(model.getName());
        //ore.setObjectOre(model.getObjectOres().stream().map(ObjectOreEntity::toEntity).collect(Collectors.toList()));
        return ore;
    }
    public static OreEntity toEntityLow (Ore model){
        OreEntity ore = new OreEntity();
        ore.setId(model.getId());
        ore.setName(model.getName());
        //ore.setObjectOre(model.getObjectOres().stream().map(ObjectOreEntity::toEntity).collect(Collectors.toList()));
        return ore;
    }
    public static OreEntity toOre(ObjectOreEntity obj){
        OreEntity ore = new OreEntity();
        ore.setId(obj.getOre().getId());
        ore.setName(obj.getOre().getName());
        List l = new ArrayList<ObjectOreEntity>();
        l.add(obj);
        ore.setObjectOre(l);
        return ore;
    }
}
