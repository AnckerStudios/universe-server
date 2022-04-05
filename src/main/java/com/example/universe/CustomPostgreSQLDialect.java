package com.example.universe;

import com.example.universe.entity.SatelliteEntity;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import liquibase.datatype.core.UUIDType;
import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.type.UUIDBinaryType;
import org.hibernate.type.UUIDCharType;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Types;
import java.util.UUID;

public class CustomPostgreSQLDialect extends PostgreSQL10Dialect {
    @Autowired
    public CustomPostgreSQLDialect() {
        super();
        //registerHibernateType(Types.OTHER, UUIDBinaryType.class.getName());
        registerHibernateType(Types.OTHER, UUID.class.getName());

        //registerHibernateType(Types.OTHER, UUIDCharType.class.getName());
        //registerHibernateType(Types.OTHER, UUIDType.class.getName());
    }
}
