package com.bank.authservice.payload;

public class JwtResponse {
    private String token;
    private String type;
    private String username;

    public JwtResponse(String token, String type, String username) {
        this.token = token;
        this.type = type;
        this.username = username;
    }

    public String getToken() { return token; }
    public String getType() { return type; }
    public String getUsername() { return username; }
}
