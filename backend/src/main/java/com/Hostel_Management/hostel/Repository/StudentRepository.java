package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  // Find students whose contracts have already expired
  List<Student> findByContractEndDateBefore(LocalDate date);

  // Find all students allocated to a specific room
  List<Student> findByRoom(Room room);

  // ✅ Find all students who are currently allocated (room is not null)
  @Query("SELECT s FROM Student s WHERE s.room IS NOT NULL")
  List<Student> findCurrentlyAllocatedStudents();
}
