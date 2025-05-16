package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.UserResponseDTO;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.common.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    //생성자 주입
    public UserController(UserService userService){
        this.userService = userService;
    }
/*
    // 유저 저장 (POST /users)
    @PostMapping
    public User createUser(@RequestParam String username, @RequestParam String password) {
        return userService.saveUser(username, password);
    }
*/

 /*
    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO request) {
        return userService.saveUser(request);
}
*/

    @PostMapping
    public ApiResponse<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        UserResponseDTO result = userService.saveUser(request);
        return ApiResponse.success(result);
    }

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginRequestDTO request) {
        String result = userService.login(request);
        return ApiResponse.success(result + "님 로그인 성공");
    }
    // 유저 전체 조히 (GET /users)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getALLUsers();
    }
}
