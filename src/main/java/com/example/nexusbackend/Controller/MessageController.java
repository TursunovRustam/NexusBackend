package com.example.nexusbackend.Controller;

import com.example.nexusbackend.DTO.Request.SendMessageRequest;
import com.example.nexusbackend.Entity.Chat;
import com.example.nexusbackend.Entity.Message;
import com.example.nexusbackend.Entity.User;
import com.example.nexusbackend.Repositories.ChatRepository;
import com.example.nexusbackend.Repositories.MessageRepository;
import com.example.nexusbackend.Services.MessageService.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageServiceImpl messageService;

    @MessageMapping("/chat")
    public void sendMessage(
            SendMessageRequest request,
            Principal principal
    ) {
        messageService.sendMessage(request, principal)
    }

}
