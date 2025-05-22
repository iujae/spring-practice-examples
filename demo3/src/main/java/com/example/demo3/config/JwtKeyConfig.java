package com.example.demo3.config;

import com.example.demo3.jwt.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtKeyConfig {

    @PostConstruct
    public void initRsaKeys() throws Exception {
        // 🔐 RSA 2048bit 키쌍 생성
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        // 🔧 JwtUtil에 주입
        RSAPublicKey publicKey = (RSAPublicKey) pair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) pair.getPrivate();

        JwtUtil.initKeys(publicKey, privateKey);

        System.out.println("✅ RSA 키 초기화 완료");
    }
}