package com.example.nexusbackend.Services.AuthService;

import com.example.nexusbackend.DTO.Request.UserReq;
import com.example.nexusbackend.Entity.Role;
import com.example.nexusbackend.Entity.User;
import com.example.nexusbackend.Enum.UserRoles;
import com.example.nexusbackend.Repositories.RoleRepository;
import com.example.nexusbackend.Repositories.UserRepository;
import com.example.nexusbackend.Services.JWTService.JWTServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final UserDetailsService userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTServiceImpl jwtService;

    @Override
    public HttpEntity<?> register(UserReq userReq) {
        List<Role> userRole = roleRepo.findByName(UserRoles.ROLE_USER);
        User newUser = User.builder().phone(userReq.getPhone())
                .roles(userRole)
                .password(passwordEncoder.encode(userReq.getPassword()))
                .build();
        userRepo.save(newUser);
        return ResponseEntity.ok(null);
    }

    @Override
    public HttpEntity<?> login(UserReq userReq) {
        User user = userRepo.findByPhone(userReq.getPhone()).orElseThrow();
        Map<String, Object> res = new HashMap<>();
        res.put("access_token", jwtService.generateToken(user));
        res.put("refresh_token", jwtService.generateRefreshToken(user));
        return ResponseEntity.ok(res);
    }

    @Override
    public HttpEntity<?> refresh(String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        return null;
    }

}
