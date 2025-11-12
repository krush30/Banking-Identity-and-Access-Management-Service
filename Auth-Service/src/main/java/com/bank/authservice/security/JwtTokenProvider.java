package com.bank.authservice.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;


@Component
public class JwtTokenProvider {


private final Key key;
private final long validityInMillis;


public JwtTokenProvider(@Value("${jwt.secret}") String secret,
@Value("${jwt.expiration-ms}") long validityInMillis) {
this.key = Keys.hmacShaKeyFor(secret.getBytes());
this.validityInMillis = validityInMillis;
}


public String generateToken(String username) {
Date now = new Date();
Date expiry = new Date(now.getTime() + validityInMillis);


return Jwts.builder()
.setSubject(username)
.setIssuedAt(now)
.setExpiration(expiry)
.signWith(key)
.compact();
}


public String getUsername(String token) {
return Jwts.parserBuilder().setSigningKey(key).build()
.parseClaimsJws(token)
.getBody()
.getSubject();
}


public boolean validateToken(String token) {
try {
Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
return true;
} catch (JwtException | IllegalArgumentException ex) {
return false;
}
}
}