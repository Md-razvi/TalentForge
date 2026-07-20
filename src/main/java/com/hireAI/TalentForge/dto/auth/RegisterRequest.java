package com.hireAI.TalentForge.dto.auth;

import com.hireAI.TalentForge.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    @NotBlank
    @Email
    private String email;

    private String password;
    private String confirmPassword;
    private Role role;
}
