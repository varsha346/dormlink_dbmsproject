package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.Dto.LeaveDto;
import com.Hostel_Management.hostel.Repository.LeaveRepository;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.models.Leave;
import com.Hostel_Management.hostel.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Convert Entity -> DTO
    public LeaveDto convertToDTO(Leave leave) {
        return new LeaveDto(
                leave.getLeaveId(),
                leave.getStudent().getStuId(),
                leave.getStudent().getUser().getName(),
                leave.getStartDate(),
                leave.getEndDate(),
                leave.getReason(),
                leave.getStatus()
        );
    }

    // Convert DTO -> Entity
    public Leave convertToEntity(LeaveDto dto) {
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Leave leave = new Leave();
        leave.setStudent(student);
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setStatus(dto.getStatus());
        return leave;
    }

    // Add Leave
    public LeaveDto addLeave(LeaveDto dto) {
        Leave leave = convertToEntity(dto);
        Leave saved = leaveRepository.save(leave);
        return convertToDTO(saved);
    }

    // Get All Leaves
    public List<LeaveDto> getAllLeaves() {
        return leaveRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get leaves by Student ID
    public List<LeaveDto> getLeavesByStudent(Long studentId) {
        return leaveRepository.findByStudent_StuId(studentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Update Leave
    public LeaveDto updateLeave(Long leaveId, LeaveDto dto) {
        Optional<Leave> leaveOpt = leaveRepository.findById(leaveId);
        if (leaveOpt.isPresent()) {
            Leave existing = leaveOpt.get();
            existing.setStartDate(dto.getStartDate());
            existing.setEndDate(dto.getEndDate());
            existing.setReason(dto.getReason());
            existing.setStatus(dto.getStatus());
            Leave saved = leaveRepository.save(existing);
            return convertToDTO(saved);
        }
        throw new RuntimeException("Leave not found");
    }

    // Delete Leave
    public void deleteLeave(Long leaveId) {
        leaveRepository.deleteById(leaveId);
    }

    // Auto-delete expired leaves
    @Scheduled(cron = "0 0 0 * * ?")
    public void autoDeleteExpiredLeaves() {
        leaveRepository.deleteByEndDateBefore(LocalDate.now());
    }
}
