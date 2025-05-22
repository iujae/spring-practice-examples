package com.example.demo3.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // 토큰 없으면 그냥 다음 필터로
        }

        try {
            String token = authHeader.replace("Bearer ", "");
            String username = JwtUtil.validateTokenAndGetUsername(token);

            // 🔐 SecurityContext에 인증 정보 설정
            List<GrantedAuthority> authorities = Collections.emptyList();
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    username,
                    "",
                    authorities
            );
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            // 예외 발생 시 로그만 남기고 필터 계속 진행
            logger.warn("JWT 인증 실패: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
