package com.example.nexusbackend.Controller;

import com.example.nexusbackend.DTO.Request.CreateChatContactReq;
import com.example.nexusbackend.Entity.User;
import com.example.nexusbackend.Services.ContactService.ContactServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactServiceImpl contactService;

    @PostMapping("/create")
    public HttpEntity<?> createContact(@AuthenticationPrincipal User user, @RequestBody CreateChatContactReq req){
        return contactService.createContact(user, req);
    }

    @GetMapping("/my_contacts")
    public HttpEntity<?> fetchMyContacts(@AuthenticationPrincipal User user){
       return contactService.getContactsByUserId(user.getId());
    }
}
