package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    public enum ComplaintStatus {
        Pending,
        Processing,
        Resolved,
        Rejected
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compId;

    @ManyToOne
    @JoinColumn(name = "stu_id", nullable = false)
    private Student student;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintStatus status;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Automatically set createdAt when complaint is saved
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
