package com.Hostel_Management.hostel.Dto;

import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.RoomReview;
import com.Hostel_Management.hostel.models.Student;

import java.util.List;

public class RoomResponseDTO {
    private Long roomNo;
    private Room.RoomCategory category;
    private int size;
    private double fees;
    private int currOccu;
    private String description;
    private List<String> photos;
    private List<RoomReview> reviews;
    private List<Student> allocatedStudents;

    public RoomResponseDTO(Room room, List<Student> students) {
        this.roomNo = room.getRoomNo();
        this.category = room.getCategory();
        this.size = room.getSize();
        this.fees = room.getFees();
        this.currOccu = room.getCurrOccu();
        this.description = room.getDescription();
        this.photos = room.getPhotos();
        this.reviews = room.getReviews();
        this.allocatedStudents = students;
    }

    // Getters
    public Long getRoomNo() { return roomNo; }
    public Room.RoomCategory getCategory() { return category; }
    public int getSize() { return size; }
    public double getFees() { return fees; }
    public int getCurrOccu() { return currOccu; }
    public String getDescription() { return description; }
    public List<String> getPhotos() { return photos; }
    public List<RoomReview> getReviews() { return reviews; }
    public List<Student> getAllocatedStudents() { return allocatedStudents; }
}
