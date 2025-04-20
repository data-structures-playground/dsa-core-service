package com.dsa.core.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProfileRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String bio;
}
