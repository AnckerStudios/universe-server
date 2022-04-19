package com.example.universe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.management.relation.Role;
import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {
}
