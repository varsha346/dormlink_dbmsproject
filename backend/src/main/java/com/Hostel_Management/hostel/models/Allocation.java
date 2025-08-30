package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "allocation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Allocation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "all_id")
    private Long allId;

    @ManyToOne
    @JoinColumn(name = "stu_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_no", nullable = false)
    private Room room;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date" ,nullable = false)
    private LocalDate endDate;
}
