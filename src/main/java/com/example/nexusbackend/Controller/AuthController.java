package com.example.nexusbackend.Controller;


import com.example.nexusbackend.DTO.Request.UserReq;
import com.example.nexusbackend.Services.AuthService.AuthServiceImpl;
import com.example.nexusbackend.Services.JWTService.JWTServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImpl authService;
    private final JWTServiceImpl jwtService;

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody UserReq user) {
        return authService.login(user);
    }

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody UserReq user) {
        return authService.register(user);
    }

    @PostMapping("/refresh")
    public HttpEntity<?> refresh(@RequestParam String refreshToken) {
        return authService.refresh(refreshToken);
    }

}
