package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "user"
//        uniqueConstraints = {
//        @UniqueConstraint(name = "unique_user_email",columnNames = {"email"}),
//        @UniqueConstraint(name = "unique_user_password",columnNames = {"password"})
//        }
//      indexes = {
//        @Index(name = "idx_user_name",columnList = "name")
//        }

)
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    public enum UserType {
        Student,
        Warden
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @NotNull
    @Email(message = "The input must be valid email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

    // ✅ Required for Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Here we are giving role-based access using userType
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userType.name()));
    }

    @Override
    public String getUsername() {
        return email; // Spring Security uses this as the login identifier
    }

    @Override
    public String getPassword() {
        return password;
    }

    // ✅ These are kept as always true for now (can customize later)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
