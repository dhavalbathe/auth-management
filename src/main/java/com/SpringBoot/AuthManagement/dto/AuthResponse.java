package com.SpringBoot.AuthManagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String jwt_token;
}
