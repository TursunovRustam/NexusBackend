package com.example.nexusbackend.Services.ChatService;

import com.example.nexusbackend.DTO.Request.CreateChatReq;
import com.example.nexusbackend.Entity.User;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface ChatService {
    HttpEntity<?> fetchChats(UUID userId);
    HttpEntity<?> createChat(User user, CreateChatReq chatInfo);
}
