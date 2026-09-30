package com.example.nexusbackend.Services.ChatService;

import com.example.nexusbackend.DTO.Request.CreateChatContactReq;
import com.example.nexusbackend.Entity.Chat;
import com.example.nexusbackend.Entity.User;
import com.example.nexusbackend.Repositories.ChatRepository;
import com.example.nexusbackend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public HttpEntity<?> fetchChats(UUID userId) {
        List<Chat> res = chatRepository.findByUsers_Id(userId);
        return ResponseEntity.ok(res);
    }

    @Override
    public HttpEntity<?> createChat(User user, CreateChatContactReq chatInfo) {
        User companion  = userRepository.findByPhone(chatInfo.getPhone())
                .orElseThrow(() -> new BadCredentialsException("User is not found"));
        chatRepository.save(Chat.builder()
                        .name(chatInfo.getChatName())
                        .users(List.of(user, companion))
                .build());
        return ResponseEntity.ok("Chat created");
    }
}
