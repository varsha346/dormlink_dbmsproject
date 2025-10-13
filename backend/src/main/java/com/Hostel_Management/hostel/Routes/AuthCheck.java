package com.Hostel_Management.hostel.Routes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hostel_Management.hostel.Config.JwtUtil;

@RestController
public class AuthCheck {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/auth/check")
    public ResponseEntity<String> checkToken(HttpServletRequest request) {
        String jwt = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                    break;
                }
            }
        }
        String username = jwtUtil.extractUsername(jwt);
        if (jwt == null || username == null || !jwtUtil.validateToken(jwt, username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired");
        }
        else if(jwtUtil.validateToken(jwt, username)){
            return ResponseEntity.status(HttpStatus.OK).body("valid session");

        }

        return ResponseEntity.ok("Valid");
    }
}
