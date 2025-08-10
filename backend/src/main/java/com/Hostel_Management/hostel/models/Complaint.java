package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "complaint")
@Data
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compId;

    @ManyToOne
    @JoinColumn(name = "stu_id", nullable = false)
    private Student student;

    @NotNull
    @Column(nullable = false)
    private String status;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String subject;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;
}

