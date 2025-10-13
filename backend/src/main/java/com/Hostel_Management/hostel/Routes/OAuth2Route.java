package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Repository.UserRepo;
import com.Hostel_Management.hostel.Services.AuthService;
import com.Hostel_Management.hostel.models.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class OAuth2Route {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthService authService;

    @GetMapping("/oauth2/success")
    public void googleLoginSuccess(@AuthenticationPrincipal OAuth2User principal, HttpServletResponse response) throws IOException {
        String email = principal.getAttribute("email");
        String name = principal.getAttribute("name");

        // Check if user exists, if not -> create
        User user = userRepo.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setPassword("");
           // User.UserType str;
            // not required for Google login
            newUser.setUserType(User.UserType.valueOf("Student")); // default role
            return userRepo.save(newUser);
        });

        // Generate JWT token
        String token = authService.generateTokenForOAuthUser(email, name);

        // Set token as cookie
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        // Redirect to frontend dashboard
        response.sendRedirect("http://localhost:3000/student-dashboard");
    }
}
