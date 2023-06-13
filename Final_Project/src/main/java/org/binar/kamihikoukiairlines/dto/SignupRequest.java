package org.binar.kamihikoukiairlines.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 50)
    private String phoneNumber;

    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    private Set<String> role;

}

