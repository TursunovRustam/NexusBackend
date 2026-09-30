package com.example.nexusbackend.Services.ContactService;

import com.example.nexusbackend.DTO.Request.CreateChatContactReq;
import com.example.nexusbackend.Entity.User;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface ContactService {
    HttpEntity<?> getContactsByUserId(UUID user_id);

    HttpEntity<?> createContact(User user, CreateChatContactReq req);
}
