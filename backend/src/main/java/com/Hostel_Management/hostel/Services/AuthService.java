package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.models.User;
import com.Hostel_Management.hostel.Repository.UserRepo;
import com.Hostel_Management.hostel.Config.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MailService mailService;

    // ---------------- LOGIN ----------------
    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user);
    }

    // ---------------- REGISTER ----------------
    public String register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    // ---------------- FORGOT PASSWORD ----------------
    public String generateResetToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("purpose", "reset");

        long resetExpiration = 15 * 60 * 1000; // 15 minutes

        String resetToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + resetExpiration))
                .signWith(jwtUtil.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        // Send email
        String resetLink = "http://localhost:3000/reset-password?token=" + resetToken; // your frontend link
        String subject = "Password Reset Request";
        String body = "Hello " + user.getName() + ",\n\n"
                + "You requested a password reset. Click the link below to reset your password:\n"
                + resetLink + "\n\n"
                + "This link will expire in 15 minutes.\n\n"
                + "If you didn't request this, please ignore this email.";

        mailService.sendSimpleMail(user.getEmail(), subject, body);

        return "Reset link sent to your email.";
    }

    // ---------------- RESET PASSWORD ----------------
    public String resetPassword(String token, String newPassword) {
        String email = jwtUtil.extractUsername(token);

        if (email == null || !jwtUtil.validateToken(token, email)) {
            throw new RuntimeException("Invalid or expired reset token");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password reset successfully";
    }
}