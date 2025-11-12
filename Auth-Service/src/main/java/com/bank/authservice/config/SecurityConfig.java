package com.bank.authservice.config;


import com.bank.authservice.security.JwtAuthenticationFilter;
import com.bank.authservice.security.JwtTokenProvider;
import com.bank.authservice.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


private final JwtTokenProvider jwtTokenProvider;
private final CustomUserDetailsService userDetailsService;


public SecurityConfig(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService userDetailsService) {
this.jwtTokenProvider = jwtTokenProvider;
this.userDetailsService = userDetailsService;
}


@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);


http
.csrf().disable()
.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
.and()
.authorizeHttpRequests()
.requestMatchers("/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
.anyRequest().authenticated()
.and()
.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


return http.build();
}


@Bean
public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}


@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
return configuration.getAuthenticationManager();
}
}