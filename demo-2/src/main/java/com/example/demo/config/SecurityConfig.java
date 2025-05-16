package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //인증 없이 접근 허용
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        http
                .csrf(crsf -> crsf.disable()) // crsf 보안 비활성화 (POST 요청용)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest() .permitAll()

                ) // 모든 요청 인증 없이 허용
                .formLogin(form ->form.disable());

        return http.build();
    }
}
