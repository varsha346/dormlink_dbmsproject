package com.Hostel_Management.hostel.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data

@NoArgsConstructor
public class AllocationDTO {
    private Long stuId;
    private String studentName;
    private String year;
    private LocalDate contractEndDate;
    private String roomNo;

    public AllocationDTO(Long stuId, String studentName, String year, LocalDate contractEndDate, String roomNo) {
        this.stuId = stuId;
        this.studentName = studentName;
        this.year = year;
        this.contractEndDate = contractEndDate;
        this.roomNo = roomNo;
    }
}
