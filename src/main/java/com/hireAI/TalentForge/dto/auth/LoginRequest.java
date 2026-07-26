package com.hireAI.TalentForge.dto.auth;

import com.hireAI.TalentForge.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest{
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;


}
