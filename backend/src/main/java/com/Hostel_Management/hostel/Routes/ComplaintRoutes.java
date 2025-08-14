package com.Hostel_Management.hostel.Routes;


import com.Hostel_Management.hostel.models.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Hostel_Management.hostel.Services.ComplaintService;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintRoutes {

    @Autowired
    private ComplaintService complaintService;

    // Create a new leave
    @PostMapping("/add")
    public Complaint createLeave(@RequestBody Complaint complaint) {
        return complaintService.addComplaint(complaint);
    }

    // Get all leaves
    @GetMapping("/all")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }


    // Get leaves by Student ID
    @GetMapping("/{studentId}")
    public List<Complaint> getLeavesByStudentId(@PathVariable Long studentId) {
        return complaintService.getComplaintsByStudent(studentId);
    }

    // Update a leave
    @PutMapping("/{id}")
    public Complaint updateLeave(@PathVariable Long id,  @RequestParam String status) {
        return complaintService.updateComplaintStatus(id, status);
    }

    // Delete a leave by ID
    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}
