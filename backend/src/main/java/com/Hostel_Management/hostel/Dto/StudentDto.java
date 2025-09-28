package com.Hostel_Management.hostel.Dto;

import lombok.Data;

@Data
public class StudentDto {
    // Student fields
    private String contact;
    private String guardianContact;
    private String dept;
    private String address;
    private String year;

    // User fields
    private String name;
    private String email;
}