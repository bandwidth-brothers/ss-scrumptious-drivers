package com.ss.scrumptious_drivers.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDto {
    @Email(message="Email is not valid")
    private String email;
    private String password;
}