
package com.Hostel_Management.hostel.Dto;

import com.Hostel_Management.hostel.models.Leave;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveStatusDto {
    private Leave.LeaveStatus status;
}
