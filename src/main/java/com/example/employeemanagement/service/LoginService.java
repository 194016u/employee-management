package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.AdminDTO;
import com.example.employeemanagement.entity.Admin;
import com.example.employeemanagement.repo.AdminRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
@Transactional
@Slf4j
public class LoginService {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.ms}")
    private long jwtExpirationMs;

    public ResponseEntity signIn(AdminDTO adminDTO) {
        Admin admin = adminRepo.findByUserName(adminDTO.getUserName());
        log.error("vvvvvvvvvv {}", admin);

        if (admin != null && passwordEncoder.matches(adminDTO.getPassword(), admin.getPassword())) {
            // Authentication successful
            String token = generateJwtToken(adminDTO.getUserName());
            return ResponseEntity.ok(token);
        } else {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // Modify your method to use a secure key
    private String generateJwtToken(String username) {
        // Generate a secure key with HS512 algorithm
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }
}
