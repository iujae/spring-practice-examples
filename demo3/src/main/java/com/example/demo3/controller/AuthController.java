package com.example.demo3.controller;

import com.example.demo3.entity.User;
import com.example.demo3.jwt.JwtUtil;
import com.example.demo3.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // ✅ 회원가입 API
    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, encodedPassword);
        userRepository.save(newUser);

        return "회원가입 성공";
    }


    // ✅ 로그인 → JWT 발급
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        /* 여기선 임시 하드코딩된 사용자 검증
        if ("iujae".equals(username) && "1234".equals(password)) {
            return JwtUtil.createToken(username);
        } else {
            throw new IllegalArgumentException("인증 실패: 아이디 또는 비밀번호가 틀렸습니다.");
        }
        */
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return JwtUtil.createToken(username);

    }

    // ✅ JWT 토큰 인증 확인용 테스트 API
    @GetMapping("/secure-test")
    public String secureTest(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = JwtUtil.validateTokenAndGetUsername(token);
        return "안녕하세요, " + username + "님!";
    }

    // 인증된 사용자 요청하여 확인
    @GetMapping("/user/me")
    public String getMyInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return "현재 로그인한 사용자: " + userDetails.getUsername();
    }
}