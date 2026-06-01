package com.example.demo.service;

import com.example.demo.DTOs.request.LoginResquestDTO;
import com.example.demo.repository.AdminRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class LoginService {

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    private AdminRepository adminRepository;

    public Boolean validatorLogin(LoginResquestDTO dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();
        if (adminRepository.existsByEmailAndPassword(email, password)) {
            return true;
        }else{
            return false;
        }
    }

    public String generateToken(LoginResquestDTO dto) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder()
                .setSubject(dto.getEmail())
                .claim("role", "ADMIN")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
        return token;
    }
}