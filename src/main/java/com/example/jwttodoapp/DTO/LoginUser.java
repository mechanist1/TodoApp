package com.example.jwttodoapp.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUser {

    String name;
    @Email
    String email;

    @NotBlank
    @Size(min = 6)
    String password;
}
