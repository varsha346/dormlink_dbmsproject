package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.Repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {

    @Autowired
    private AllocationRepository allocationRepository;

    // Get allocation history by student ID
    public List<Allocation> getAllocationsByStudentId(Long stuId) {
        return allocationRepository.findByStudent_StuId(stuId);
    }
    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }
}

