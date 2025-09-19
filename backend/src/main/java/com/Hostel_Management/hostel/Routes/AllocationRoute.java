package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.Services.AllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allocation")
@RequiredArgsConstructor
public class AllocationRoute {

    private final AllocationService allocationService;

    // Get all allocations (history) for a student
    @GetMapping("/{stuId}")
    public ResponseEntity<List<Allocation>> getAllocationsByStudentId(@PathVariable Long stuId) {
        List<Allocation> allocations = allocationService.getAllocationsByStudentId(stuId);
        return ResponseEntity.ok(allocations);
    }
}

