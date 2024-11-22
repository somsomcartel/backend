package com.somsomcartel.crud.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                //.requestMatchers(HttpMethod.GET, "/post/**").permitAll()
                //.anyRequest().authenticated()
                .anyRequest().permitAll() // TODO: 로그인 작업 완료 후 삭제 예정
        );

        http.csrf(csrf -> csrf.disable()); // TODO: 로그인 작업 완료 후 삭제 예정

        http.oauth2ResourceServer(auth-> auth.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
