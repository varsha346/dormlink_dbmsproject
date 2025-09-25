package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.models.User;
import com.Hostel_Management.hostel.Services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRoute {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        String message = authService.register(user);
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body("{\"message\": \"" + message + "\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest,
                                        HttpServletResponse response) {

        // Call existing AuthService login method
        String token = authService.login(loginRequest.get("email"), loginRequest.get("password"));

        // Set JWT as HttpOnly cookie
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);           // Prevent JS access
        cookie.setSecure(false);            // true in production (HTTPS)
        cookie.setPath("/");                // Cookie valid for entire site
        cookie.setMaxAge(24 * 60 * 60);     // 1 day
        response.addCookie(cookie);

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body("{\"message\": \"Login successful\"}");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // Delete the cookie
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);            // true in production
        cookie.setPath("/");
        cookie.setMaxAge(0);                // Expire immediately
        response.addCookie(cookie);

        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body("{\"message\": \"Logged out successfully\"}");
    }
}