package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "room_review")
@NoArgsConstructor
@AllArgsConstructor
public class RoomReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "room_no", nullable = false)
    private Room room;

    @NotNull
    private Long studentId; // You can map to Student later if needed

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private int rating;  // Rating out of 5

    @Column(length = 500)
    private String comment;  // Optional feedback
}
