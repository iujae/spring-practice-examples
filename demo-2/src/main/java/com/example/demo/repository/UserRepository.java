package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends  JpaRepository<User, Long> {

    //username으로 사용자 조회 (Optional 반환)
    Optional<User> findByUsername(String username);

    //필요시 중복체크도 가능
    boolean existsByUsername(String username);

}