package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.models.Complaint;
import com.Hostel_Management.hostel.Repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    // Add complaint
    public Complaint addComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    // Delete complaint by ID (warden or student)
    public void deleteComplaint(Long compId) {
        complaintRepository.deleteById(compId);
    }

    // Get all complaints
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // Get complaints for a specific student
    public List<Complaint> getComplaintsByStudent(Long studentId) {
        return complaintRepository.findByStudent_StuId(studentId);
    }

    // Update complaint status (for warden)
    public Complaint updateComplaintStatus(Long compId, String status) {
        Optional<Complaint> complaintOpt = complaintRepository.findById(compId);
        if (complaintOpt.isPresent()) {
            Complaint existingComplaint = complaintOpt.get();
            existingComplaint.setStatus(status);
            return complaintRepository.save(existingComplaint);
        }
        return null;
    }
}

