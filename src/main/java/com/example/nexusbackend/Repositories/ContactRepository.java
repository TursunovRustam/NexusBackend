package com.example.nexusbackend.Repositories;

import com.example.nexusbackend.Entity.Chat;
import com.example.nexusbackend.Entity.Contact;
import com.example.nexusbackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    List<Contact> findAllByUser_Id(UUID userId);
    Optional<Contact> findByChat(Chat chat);
}
