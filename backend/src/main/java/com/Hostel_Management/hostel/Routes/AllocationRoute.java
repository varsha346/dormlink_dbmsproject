package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Services.AllocationService;
import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Allocation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/allocations")
@RequiredArgsConstructor
public class AllocationRoute {

    private final AllocationService allocationService;

    @GetMapping("/currentAll")
    public List<Student> getCurrentAllocations() {
        return  allocationService.getCurrentAllocations();

    }

    // ✅ 1. Filtered Current Allocations
    @GetMapping("/current")
    public List<Student> getCurrentAllocations(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate today,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String roomNo,
            @RequestParam(required = false) String studentName) {

        return allocationService.getFilteredStudents(today, year, roomNo, studentName);
    }

    // ✅ 2. Allocation History (with filters)
    @GetMapping("/history")
    public List<Allocation> getAllocationHistory(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String roomNo,
            @RequestParam(required = false) String studentName) {

        return allocationService.getFilteredAllocations(year, roomNo, studentName);
    }
}
