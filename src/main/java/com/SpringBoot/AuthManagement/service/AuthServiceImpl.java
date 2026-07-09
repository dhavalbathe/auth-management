package com.SpringBoot.AuthManagement.service;

import com.SpringBoot.AuthManagement.dto.UserRegisterDTO;
import com.SpringBoot.AuthManagement.dto.UserResponseDTO;
import com.SpringBoot.AuthManagement.entity.User;
import com.SpringBoot.AuthManagement.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserResponseDTO register(UserRegisterDTO registerUser) {
        Boolean userExists = userRepository.existsByEmail(registerUser.getEmail());

        if(userExists) throw new EntityExistsException("User with this email already exists");

        User user = new User();
        user.setName(registerUser.getName());
        user.setEmail(registerUser.getEmail());
        user.setRole(registerUser.getRole());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));

        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }
}
