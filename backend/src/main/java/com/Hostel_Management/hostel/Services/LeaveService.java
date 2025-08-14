package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.models.Leave;
import com.Hostel_Management.hostel.Repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    // Add a leave
    public Leave addLeave(Leave leave) {
        return leaveRepository.save(leave);
    }

    // Delete leave by ID (for student)
    public void deleteLeave(Long leaveId) {
        leaveRepository.deleteById(leaveId);
    }

    // Auto-delete expired leaves
    @Scheduled(cron = "0 0 0 * * ?")
    public void autoDeleteExpiredLeaves() {
        leaveRepository.deleteByEndDateBefore(LocalDate.now());
    }

    // Get all leaves (for warden)
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    // Get leaves for a specific student
    public List<Leave> getLeavesByStudent(Long studentId) {
        return leaveRepository.findByStudent_StuId(studentId);
    }

    // Update leave
    public Leave updateLeave(Long leaveId, Leave updatedLeave) {
        Optional<Leave> leaveOpt = leaveRepository.findById(leaveId);
        if (leaveOpt.isPresent()) {
            Leave existingLeave = leaveOpt.get();
            existingLeave.setStartDate(updatedLeave.getStartDate());
            existingLeave.setEndDate(updatedLeave.getEndDate());
            existingLeave.setReason(updatedLeave.getReason());
            existingLeave.setStatus(updatedLeave.getStatus());
            return leaveRepository.save(existingLeave);
        }
        return null;
    }
}
