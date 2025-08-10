package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "leave")
@Data
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @ManyToOne
    @JoinColumn(name = "stu_id", nullable = false)
    private Student student;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @Column(nullable = false)
    private String reason;

    @NotNull
    @Column(nullable = false)
    private String status;
}
