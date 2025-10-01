package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.models.Student;
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

    // Get allocation history for a specific student
    @GetMapping("/{stuId}")
    public ResponseEntity<List<Allocation>> getAllocationsByStudentId(@PathVariable Long stuId) {
        return ResponseEntity.ok(allocationService.getAllocationsByStudentId(stuId));
    }

    // Get full allocation history (all students)
    @GetMapping("/all")
    public ResponseEntity<List<Allocation>> getAllAllocations() {
        return ResponseEntity.ok(allocationService.getAllAllocations());
    }

    // Get currently allocated students
    @GetMapping("/current")
    public ResponseEntity<List<Student>> getCurrentAllocations() {
        return ResponseEntity.ok(allocationService.getCurrentAllocations());
    }
}
