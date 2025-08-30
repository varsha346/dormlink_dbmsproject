package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.models.User;
import com.Hostel_Management.hostel.Services.AuthService;
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
                .header("Content-Type", "application/json")  // ✅ header for JSON response
                .body("{\"message\": \"" + message + "\"}");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String token = authService.login(loginRequest.get("email"), loginRequest.get("password"));
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")  // ✅ header for JSON response
                .body(Map.of("token", token));
    }
}
