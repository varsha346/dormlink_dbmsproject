package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.Repository.AllocationRepository;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Allocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final StudentRepository studentRepository;

    // ✅ 1. Current allocations (all students still in hostel) without filters
    public List<Student> getCurrentAllocations() {
        return studentRepository.findByContractEndDateAfter(LocalDate.now());
    }

    // ✅ 2. Current allocations with optional filters
    public List<Student> getFilteredStudents(LocalDate today, String year, String roomNo, String studentName) {
        // If today is null, use current date
        LocalDate filterDate = (today != null) ? today : LocalDate.now();
        return studentRepository.filterCurrentAllocations(filterDate, year, roomNo, studentName);
    }

    // ✅ 3. Allocation history with optional filters
    public List<Allocation> getFilteredAllocations(Integer year, String roomNo, String studentName) {
        return allocationRepository.filterAllocations(year, roomNo, studentName);
    }
}
