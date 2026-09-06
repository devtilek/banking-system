package com.bankingsystem.authservice.service;

import com.bankingsystem.authservice.dto.RegisterRequest;
import com.bankingsystem.authservice.entity.UserCredential;
import com.bankingsystem.authservice.exception.DuplicateEmailException;
import com.bankingsystem.authservice.mapper.UserMapper;
import com.bankingsystem.authservice.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserCredentialRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public void register(RegisterRequest request){
        if (repository.findByEmail(request.email()).isPresent()){
            throw new DuplicateEmailException("User with this email already exists: " + request.email());
        }
        UserCredential user = userMapper.toEntity(request, passwordEncoder);
        repository.save(user);
    }
}
