package com.example.demo3.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;




public class JwtUtil {

    // RSA 키 (실제 서비스에서는 키파일 또는 KeyStore로부터 로딩)
    private static RSAPublicKey publicKey;
    private static RSAPrivateKey privateKey;

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    // 🔧 키 초기화 메서드
    public static void initKeys(RSAPublicKey pubKey, RSAPrivateKey privKey) {
        publicKey = pubKey;
        privateKey = privKey;
    }

    // ✅ JWT 생성
    public static String createToken(String username) {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            return JWT.create()
                    .withSubject(username)
                    .withIssuer("auth0")
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("JWT 생성 실패", e);
        }
    }

    // ✅ JWT 검증
    public static String validateTokenAndGetUsername(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, null); // 검증엔 공개키만 필요
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("JWT 검증 실패", e);
        }
    }
}