package com.Hostel_Management.hostel.Dto;

import java.time.LocalDate;

public record AllocationDTO(
        Long stuId,
        String studentName,
        String year,
        LocalDate contractEndDate,
        String roomNo,
        LocalDate startDate,   // only for history
        LocalDate endDate      // only for history
) {}
