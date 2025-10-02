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

    // ✅ 1. Current allocations (students still in hostel)
    public List<Student> getCurrentAllocations() {
        LocalDate today = LocalDate.now();

        // fetch all students from DB
        List<Student> allStudents = studentRepository.findAll();

        // filter only those whose contractEndDate is after today
        return allStudents.stream()
                .filter(student -> student.getContractEndDate() != null &&
                        student.getContractEndDate().isAfter(today))
                .toList();
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
