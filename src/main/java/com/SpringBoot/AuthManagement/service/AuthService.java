package com.SpringBoot.AuthManagement.service;

import com.SpringBoot.AuthManagement.dto.AuthResponse;
import com.SpringBoot.AuthManagement.dto.LoginRequestDTO;
import com.SpringBoot.AuthManagement.dto.UserRegisterDTO;
import com.SpringBoot.AuthManagement.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    public UserResponseDTO register(UserRegisterDTO user);

    public AuthResponse login(LoginRequestDTO request);
}
