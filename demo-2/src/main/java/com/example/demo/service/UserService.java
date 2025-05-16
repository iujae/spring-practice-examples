package com.example.demo.service;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //생성자 주입
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //유저 저장
    /*
    public User saveUser(String username, String password){
        User user = new User(username, password);
        return userRepository.save(user);
    }
    */
    public UserResponseDTO saveUser(UserRequestDTO request) {

        String username = request.getUsername();
        if (username.equalsIgnoreCase("ban") || username.contains("!")) {
            throw new IllegalArgumentException("허용되지 않은 사용자 이름입니다.");
        }

        String encrypted = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getUsername(), encrypted);
        User saved = userRepository.save(user);
        return new UserResponseDTO(saved.getId(), saved.getUsername());

    }

    //로그인 메소드
    public String login(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        boolean matched = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!matched) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user.getUsername(); // 나중에 토큰이나 세션으로 교체가능
    }


    //유저 전체 조회
    public List<User> getALLUsers() {
        return userRepository.findAll();
    }
}
