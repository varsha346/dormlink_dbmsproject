package com.Hostel_Management.hostel.Routes;


import com.Hostel_Management.hostel.models.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Hostel_Management.hostel.Services.LeaveService;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRoutes {

    @Autowired
    private LeaveService leaveService;

    // Create a new leave
    @PostMapping("/add")
    public Leave createLeave(@RequestBody Leave leave) {
        return leaveService.addLeave(leave);
    }

    // Get all leaves
    @GetMapping("/all")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }


    // Get leaves by Student ID
    @GetMapping("/{studentId}")
    public List<Leave> getLeavesByStudentId(@PathVariable Long studentId) {
        return leaveService.getLeavesByStudent(studentId);
    }

    // Update a leave
    @PutMapping("/{id}")
    public Leave updateLeave(@PathVariable Long id, @RequestBody Leave leaveDetails) {
        return leaveService.updateLeave(id, leaveDetails);
    }

    // Delete a leave by ID
    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
    }


}

