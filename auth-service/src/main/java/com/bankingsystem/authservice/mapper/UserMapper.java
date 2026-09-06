package com.bankingsystem.authservice.mapper;

import com.bankingsystem.authservice.dto.RegisterRequest;
import com.bankingsystem.authservice.entity.Role;
import com.bankingsystem.authservice.entity.UserCredential;
import com.bankingsystem.authservice.entity.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserCredential toEntity(RegisterRequest request, PasswordEncoder passwordEncoder){
        return UserCredential.builder()
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password()))
                .role(Role.CUSTOMER)
                .status(UserStatus.ACTIVE)
                .build();
    }
}
