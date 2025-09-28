package com.Hostel_Management.hostel.Routes;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import com.Hostel_Management.hostel.models.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Hostel_Management.hostel.Services.ComplaintService;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintRoutes {

    @Autowired
    private ComplaintService complaintService;

    // Create a new complaint
    @PostMapping("/add")
    public Complaint createLeave(@RequestBody Complaint complaint) {
        return complaintService.addComplaint(complaint);
    }


    @GetMapping("/all")
    public List<Map<String, Object>> getAllComplaints() {
        return complaintService.getAllComplaints(); // make sure this is the method name
    }


    // Get complaints by Student ID
    @GetMapping("/{studentId}")
    public List<Complaint> getLeavesByStudentId(@PathVariable Long studentId) {
        return complaintService.getComplaintsByStudent(studentId);
    }

    // Update a complaint
    @PutMapping("/{id}")
    public Complaint updateLeave(@PathVariable Long id,  @RequestParam String status) {
        return complaintService.updateComplaintStatus(id, status);
    }

    // Delete a complaint by ID
    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}
