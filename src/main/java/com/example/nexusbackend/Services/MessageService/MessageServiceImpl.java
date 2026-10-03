package com.example.nexusbackend.Services.MessageService;

import com.example.nexusbackend.DTO.Request.SendMessageRequest;
import com.example.nexusbackend.Entity.Chat;
import com.example.nexusbackend.Entity.Message;
import com.example.nexusbackend.Entity.User;
import com.example.nexusbackend.Repositories.ChatRepository;
import com.example.nexusbackend.Repositories.MessageRepository;
import com.example.nexusbackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;

    @Override
    public void sendMessage(SendMessageRequest request, Principal principal) {

        Chat chat = chatRepository.findById(request.getChatId())
                .orElseThrow(() -> new RuntimeException("Chat not found"));


        User sender = userRepository.findByPhone(Long.parseLong(principal.getName())).orElseThrow();

        Message message = Message.builder()
                .content(request.getContent())
                .chat(chat)
                .sender(sender)
                .build();

        messageRepository.save(message);
        for (User user : chat.getUsers()) {

            messagingTemplate.convertAndSendToUser(
                    user.getId().toString(),
                    "/queue/messages",
                    message
            );
        }
    }
}
