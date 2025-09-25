package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Dto.LeaveDto;
import com.Hostel_Management.hostel.Services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LeaveRoutes {

    @Autowired
    private LeaveService leaveService;

    // Create Leave
    @PostMapping("/add")
    public LeaveDto createLeave(@RequestBody LeaveDto leaveDto) {
        return leaveService.addLeave(leaveDto);
    }

    // Get All Leaves
    @GetMapping("/all")
    public List<LeaveDto> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    // Get Leaves by Student ID
    @GetMapping("/student/{studentId}")
    public List<LeaveDto> getLeavesByStudent(@PathVariable Long studentId) {
        return leaveService.getLeavesByStudent(studentId);
    }

    // Update Leave
    @PutMapping("/{id}")
    public LeaveDto updateLeave(@PathVariable Long id, @RequestBody LeaveDto leaveDto) {
        return leaveService.updateLeave(id, leaveDto);
    }

    // Delete Leave
    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
    }
}
