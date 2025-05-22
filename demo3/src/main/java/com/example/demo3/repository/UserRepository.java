package com.example.demo3.repository;

import com.example.demo3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // username으로 사용자 찾기 (로그인 시 사용)
    Optional<User> findByUsername(String username);
}
