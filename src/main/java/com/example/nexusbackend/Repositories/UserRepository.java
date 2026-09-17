package com.example.nexusbackend.Repositories;

import com.example.nexusbackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhone(Long phone);

    Optional<User> findByUsername(String username);
}
