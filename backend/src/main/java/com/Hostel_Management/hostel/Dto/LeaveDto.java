package com.Hostel_Management.hostel.Dto;

import com.Hostel_Management.hostel.models.Leave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDto {
    private Long leaveId;
    private Long studentId;      // input from frontend
    private String studentName;  // output to frontend
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private Leave.LeaveStatus status;
}

