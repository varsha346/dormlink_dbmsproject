package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private Long stuId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "stu_id", nullable = false)
    private User user;


    @NotNull
    @Column(nullable = false)
    private String contact;

    @ManyToOne
    @JoinColumn(name = "room_no" ,nullable = true)
    private Room room;

    @NotNull
    @Column(name = "fee_status", nullable = false)
    private Boolean feeStatus;

    @NotNull
    @Column(name = "guardian_contact", nullable = false)
    private String guardianContact;

    @NotNull
    @Column(nullable = false)
    private String dept;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private String year;

    @Column(name = "contract_end_date" ,nullable = true)
    private LocalDate contractEndDate;  // NEW FIELD  // e.g., "2025-2026"


    @Version
    private Long version;

}
