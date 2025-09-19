package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
