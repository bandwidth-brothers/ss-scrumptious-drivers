package com.ss.scrumptious_drivers.dto;

import java.sql.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDriverDto {
	@NotNull
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotNull
    @NotBlank
    private String phone;

    private Date dob;

    @NotBlank
    private String licenseNum;

    @Builder.Default
    private Float rating = 0.0f;
    
    private String picture;
    
    @Builder.Default
    private String status = "Idle";

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid.")
    private String email;

    @ToString.Exclude
    @NotNull
    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 10, message = "Length at least 10 characters.")
    private String password;

    @Valid
    @NotNull
    private CreateAddressDto address;
}