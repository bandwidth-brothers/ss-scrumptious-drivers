package com.ss.scrumptious_drivers.client;

import java.util.UUID;

import javax.validation.Valid;

import com.ss.scrumptious_drivers.dto.AuthDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


 @FeignClient("scrumptious-auth-service")
 public interface AuthClient {

	 @PostMapping(value = "/auth/driver/register")
	 ResponseEntity<UUID> createNewAccountDriver(@Valid @RequestBody AuthDto authDto);

//   @PutMapping(value = EndpointConstants.API_V_0_1_ACCOUNTS + "/customer/{customerId}",
//       consumes = MediaType.TEXT_PLAIN_VALUE)
//   ResponseEntity<Void> updateCustomerEmail(@RequestHeader(value = "Authorization")
//                                         String authorizationHeader,
//                                         @PathVariable UUID customerId,
//                                         @RequestBody String newEmail);

 }
