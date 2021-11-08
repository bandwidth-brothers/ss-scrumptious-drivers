package com.ss.scrumptious_drivers.dto;

import java.sql.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDriverDto {

    private String firstName;
    private String lastName;
    private String phone;
    private Date dob;
    private String licenseNum;
    private Float rating;
    private String picture;
    private String status;

    @Email(message = "Email must be valid.")
    private String email;

    @Valid
    private UpdateAddressDto address;
}