package com.Hostel_Management.hostel.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;
import com.Hostel_Management.hostel.models.RoomReview;


@Entity
@Data
@Table(name = "Room")
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    public enum RoomCategory {
        SINGLE,
        DOUBLE,
        TRIPLE,
        SUITE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomNo;

    @NotNull
    @Enumerated(EnumType.STRING)  // Store enum as String
    @Column(nullable = false)
    private RoomCategory category;

    @NotNull
    @Column(nullable = false)
    private int size;

    @NotNull
    @Column(nullable = false)
    private double fees;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomReview> reviews = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "room_photos", joinColumns = @JoinColumn(name = "room_no"))
    @Column(name = "photo_url")
    private List<String> photos = new ArrayList<>();

    @NotNull
    @Column(name = "curr_occu", nullable = false)
    private int currOccu;

    @Column(length = 255)
    private String description;



}
