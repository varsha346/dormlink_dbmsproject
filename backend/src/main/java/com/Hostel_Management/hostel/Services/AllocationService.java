package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.Repository.AllocationRepository;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.Hostel_Management.hostel.Dto.AllocationDTO;

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
    public List<AllocationDTO> getCurrentAllocations(String year, String roomNo, String studentName) {
        return studentRepository.filterCurrentAllocations(LocalDate.now(), year, roomNo, studentName);
    }

    public List<AllocationDTO> getAllocationHistory(Integer year, String roomNo, String studentName) {
        return allocationRepository.filterAllocations(year, roomNo, studentName);
    }
}
