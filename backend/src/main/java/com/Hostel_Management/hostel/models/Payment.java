package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;

    private String paymentId;

    private int amountPaid;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
