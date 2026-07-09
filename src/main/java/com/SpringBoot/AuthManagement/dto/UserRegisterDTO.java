package com.SpringBoot.AuthManagement.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRegisterDTO {
    private String name;

    @Email

    private String password;
}
