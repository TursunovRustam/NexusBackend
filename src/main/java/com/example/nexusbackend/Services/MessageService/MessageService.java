package com.example.nexusbackend.Services.MessageService;

import com.example.nexusbackend.DTO.Request.SendMessageRequest;

import java.security.Principal;

public interface MessageService {
    void sendMessage(SendMessageRequest request,
                              Principal principal);
}
