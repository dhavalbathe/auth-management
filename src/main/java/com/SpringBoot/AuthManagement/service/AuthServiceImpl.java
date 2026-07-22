package com.SpringBoot.AuthManagement.service;

import com.SpringBoot.AuthManagement.dto.AuthResponse;
import com.SpringBoot.AuthManagement.dto.LoginRequestDTO;
import com.SpringBoot.AuthManagement.dto.UserRegisterDTO;
import com.SpringBoot.AuthManagement.dto.UserResponseDTO;
import com.SpringBoot.AuthManagement.entity.User;
import com.SpringBoot.AuthManagement.repository.UserRepository;
import com.SpringBoot.AuthManagement.security.UserPrinciple;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final JwtService jwtService;

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

    @Override
    public AuthResponse login(LoginRequestDTO request) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return AuthResponse.builder()
                .jwt_token(token)
                .build();
    }
}
