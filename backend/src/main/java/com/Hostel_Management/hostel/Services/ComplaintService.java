package com.Hostel_Management.hostel.Services;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import com.Hostel_Management.hostel.models.Complaint;
import com.Hostel_Management.hostel.models.Complaint.ComplaintStatus;
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

//    // Get all complaints
//    public List<Complaint> getAllComplaints() {
//        return complaintRepository.findAll();
//    }
// Service method
// Get all complaints with student name
public List<Map<String, Object>> getAllComplaints() {
    List<Complaint> complaints = complaintRepository.findAll();
    List<Map<String, Object>> response = new ArrayList<>();

    for (Complaint comp : complaints) {
        Map<String, Object> map = new HashMap<>();
        map.put("compId", comp.getCompId());
        //map.put("title", comp.getTitle());
        map.put("description", comp.getDescription());
        map.put("status", comp.getStatus());
        map.put("studentId", comp.getStudent().getStuId());
        map.put("studentName", comp.getStudent().getUser().getName()); // add student name
        response.add(map);
    }

    return response;
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

            try {
                ComplaintStatus newStatus = ComplaintStatus.valueOf(status); // convert String → Enum
                existingComplaint.setStatus(newStatus);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid status: " + status);
            }

            return complaintRepository.save(existingComplaint);
        }
        return null;
    }

}

