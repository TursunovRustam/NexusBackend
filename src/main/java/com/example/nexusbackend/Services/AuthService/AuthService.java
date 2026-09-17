package com.example.nexusbackend.Services.AuthService;

import com.example.nexusbackend.DTO.Request.UserReq;
import com.example.nexusbackend.Repositories.UserRepository;
import org.springframework.http.HttpEntity;

public interface AuthService {
    HttpEntity<?> register(UserReq userReq);
    HttpEntity<?> login(UserReq userReq);

    HttpEntity<?> refresh(String refreshToken);
}
