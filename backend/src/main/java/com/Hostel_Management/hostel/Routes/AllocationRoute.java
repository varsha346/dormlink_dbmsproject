package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Services.AllocationService;
import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
@RequiredArgsConstructor
public class AllocationRoute {

    private final AllocationService allocationService;

    // ✅ 1. Current Allocations
    @GetMapping("/current")
    public List<Student> getCurrentAllocations() {
        return allocationService.getCurrentAllocations();
    }

    // ✅ 2. Allocation History (with filters)
    @GetMapping("/history")
    public List<Allocation> filterAllocationHistory(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String roomNo,
            @RequestParam(required = false) String studentName) {
        return allocationService.filterAllocationHistory(year, roomNo, studentName);
    }
}