package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.Repository.AllocationRepository;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {

    @Autowired
    private AllocationRepository allocationRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Get allocation history for a student
    public List<Allocation> getAllocationsByStudentId(Long stuId) {
        return allocationRepository.findByStudent_StuId(stuId);
    }

    // Get all allocation history
    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    // Get current allocations (students currently assigned to rooms)
    public List<Student> getCurrentAllocations() {
        return studentRepository.findCurrentlyAllocatedStudents();
    }
}
