package com.example.crash.service;


import io.jsonwebtoken.Jwts;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;

public class jwtService {
    private final SecretKey key;

    public jwtService(@Value("${jwt.secret-key}") String key){
        this.key = key;
    }

//    SecretKey key = Jwts.SIG.HS256.key().build();
//    String jws = Jwts.builder().subject("Joe").signWith(key).compact();
}
