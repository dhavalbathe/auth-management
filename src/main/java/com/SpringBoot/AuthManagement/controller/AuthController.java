package com.SpringBoot.AuthManagement.controller;

import com.SpringBoot.AuthManagement.dto.AuthResponse;
import com.SpringBoot.AuthManagement.dto.LoginRequestDTO;
import com.SpringBoot.AuthManagement.dto.UserRegisterDTO;
import com.SpringBoot.AuthManagement.dto.UserResponseDTO;
import com.SpringBoot.AuthManagement.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRegisterDTO registerUser) {
        return ResponseEntity.ok(authService.register(registerUser));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
