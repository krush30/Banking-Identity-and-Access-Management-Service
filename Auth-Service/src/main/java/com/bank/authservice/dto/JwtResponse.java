package com.bank.authservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class JwtResponse {
private String token;
private String tokenType = "Bearer";
private String username;
}