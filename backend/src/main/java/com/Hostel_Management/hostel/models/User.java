package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @NotNull
    @Email(message = "The input must be valid email")
    @Column(nullable = false ,unique = true)
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String password;

}
