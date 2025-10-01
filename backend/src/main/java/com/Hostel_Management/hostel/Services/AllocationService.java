package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.Repository.AllocationRepository;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final StudentRepository studentRepository;

    // ✅ Current allocations = students still in hostel
    public List<Student> getCurrentAllocations() {
        return studentRepository.findByContractEndDateAfter(LocalDate.now());
    }

    // ✅ Past allocations = allocation history (with filters)
    public List<Allocation> filterAllocationHistory(Integer year, String roomNo, String studentName) {
        return allocationRepository.filterAllocations(year, roomNo, studentName);
    }
}
