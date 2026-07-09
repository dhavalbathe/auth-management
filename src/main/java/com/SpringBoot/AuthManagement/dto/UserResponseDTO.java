package com.SpringBoot.AuthManagement.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UserResponseDTO {
    private String name;
    private String email;
    private String role;
}
