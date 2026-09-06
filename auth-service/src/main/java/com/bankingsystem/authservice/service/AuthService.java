package com.bankingsystem.authservice.service;

import com.bankingsystem.authservice.config.JwtUtils;
import com.bankingsystem.authservice.dto.LoginRequest;
import com.bankingsystem.authservice.entity.UserCredential;
import com.bankingsystem.authservice.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public String login(LoginRequest request){
        UserCredential user = repository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())){
            throw new RuntimeException("Invalid email or password");
        }

        return jwtUtils.generateToken(user);
    }
}
