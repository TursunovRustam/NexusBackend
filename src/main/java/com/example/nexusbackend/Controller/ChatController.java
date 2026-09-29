package com.example.nexusbackend.Controller;


import com.example.nexusbackend.DTO.Request.CreateChatReq;
import com.example.nexusbackend.Entity.User;
import com.example.nexusbackend.Services.ChatService.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/chat")
public class ChatController {
    private final ChatServiceImpl chatService;

    @GetMapping("/get")
    public ResponseEntity<?> fetchChats(@AuthenticationPrincipal User user) {
        UUID userId = user.getId();
        return ResponseEntity.ok(chatService.fetchChats(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createChat(@AuthenticationPrincipal User user, @RequestBody CreateChatReq chatInfo) {
        return ResponseEntity.ok(chatService.createChat(user, chatInfo));
    }
}
