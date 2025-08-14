package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByStudent_StuId(Long studentId);
    void deleteByEndDateBefore(LocalDate date);
}
