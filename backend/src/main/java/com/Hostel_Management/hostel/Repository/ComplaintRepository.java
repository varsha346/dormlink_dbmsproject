package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByStudent_StuId(Long studentId);
}

