package com.example.nexusbackend.Services.ContactService;

import com.example.nexusbackend.DTO.Request.CreateChatContactReq;
import com.example.nexusbackend.Entity.Chat;
import com.example.nexusbackend.Entity.Contact;
import com.example.nexusbackend.Entity.User;
import com.example.nexusbackend.Repositories.ChatRepository;
import com.example.nexusbackend.Repositories.ContactRepository;
import com.example.nexusbackend.Repositories.UserRepository;
import com.example.nexusbackend.Services.ChatService.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public HttpEntity<?> getContactsByUserId(UUID user_id) {
        return ResponseEntity.ok(contactRepository.findAllByUser_Id(user_id));
    }


    @Override
    public HttpEntity<?> createContact(User user1, CreateChatContactReq req) {
        System.out.println("hello");
        User user2 = userRepository.findByPhone(req.getPhone())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User is not found"
                        )
                );
        Chat chat = chatRepository.findChatByUsers(user1.getId(), user2.getId())
                .orElseGet(() -> chatRepository.save(
                        Chat.builder()
                                .name(req.getChatName())
                                .users(List.of(user1, user2))
                                .build()
                ));
        Optional<Contact> contact = contactRepository.findByChat(chat);
        if (contact.isEmpty()) {
            return ResponseEntity.ok(contactRepository.save(Contact.builder().name(req.getChatName()).user(user1).chat(chat).build()));
        } else {
            return ResponseEntity.ok("Contact already exists");
        }

    }
}
