package com.example.nexusbackend.Repositories;

import com.example.nexusbackend.Entity.Role;
import com.example.nexusbackend.Enum.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(UserRoles name);
}
