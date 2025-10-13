package com.Hostel_Management.hostel.Routes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hostel_Management.hostel.Config.JwtUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@RestController
public class AuthCheck {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/auth/check")
    public ResponseEntity<Map<String, Object>> checkToken(HttpServletRequest request) {
        String jwt = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }

        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Token missing"));
        }

        String username = jwtUtil.extractUsername(jwt);
        if (username == null || !jwtUtil.validateToken(jwt, username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Token expired"));
        }

        Map<String, Object> res = new HashMap<>();
        res.put("message", "valid session");
        res.put("userType", jwtUtil.extractClaim(jwt, c -> c.get("userType", String.class)));
        return ResponseEntity.ok(res);
    }
}