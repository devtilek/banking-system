package com.bankingsystem.authservice.controller;

import com.bankingsystem.authservice.dto.AuthResponse;
import com.bankingsystem.authservice.dto.LoginRequest;
import com.bankingsystem.authservice.dto.RegisterRequest;
import com.bankingsystem.authservice.service.AuthService;
import com.bankingsystem.authservice.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody RegisterRequest request){
        registrationService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request){
        String token = authService.login(request);
        return new AuthResponse(token);
    }
}
