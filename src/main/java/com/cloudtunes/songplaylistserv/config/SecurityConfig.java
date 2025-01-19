package com.cloudtunes.songplaylistserv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/test/**").permitAll() // Allow all connections to /test
                                .requestMatchers("/album/**").permitAll() // Allow all connections to /album
                                .requestMatchers("/playlist/**").permitAll() // Allow all connections to /playlist
                                .requestMatchers("/song/**").permitAll() // Allow all connections to /song
                                .requestMatchers("/user/**").permitAll() // Allow all connections to /user
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection if not needed
                .formLogin(formLogin -> formLogin.disable()) // Disable form login
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic authentication
        return http.build();
    }
}