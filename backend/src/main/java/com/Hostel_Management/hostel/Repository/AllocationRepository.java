package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    List<Allocation> findByStudent_StuId(Long studentId);
}
