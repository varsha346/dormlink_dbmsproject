package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNo;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private RoomType roomType; // Association with RoomType

    @NotNull
    @Column(name = "curr_occu", nullable = false)
    private int currOccu;
}
