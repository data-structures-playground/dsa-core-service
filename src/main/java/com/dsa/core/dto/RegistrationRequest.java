package com.dsa.core.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String email;
    // You might add fields for profile creation here as well,
    // or handle profile creation in a separate step.
}
