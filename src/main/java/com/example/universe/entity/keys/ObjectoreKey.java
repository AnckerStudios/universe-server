package com.example.universe.entity.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ObjectoreKey implements Serializable {
    public UUID getObjectId() {
        return objectId;
    }

    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    public UUID getOreId() {
        return oreId;
    }

    public void setOreId(UUID oreId) {
        this.oreId = oreId;
    }

    public ObjectoreKey(UUID objectId, UUID oreId) {
        this.objectId = objectId;
        this.oreId = oreId;
    }
    public ObjectoreKey(){

    }

    @Column(name="fk2_obj_uuid")
    UUID objectId;
    @Column(name="fk2_ore_uuid")
    UUID oreId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectoreKey that = (ObjectoreKey) o;
        return Objects.equals(objectId, that.objectId) && Objects.equals(oreId, that.oreId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId, oreId);
    }
}
