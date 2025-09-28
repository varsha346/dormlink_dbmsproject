package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  //  List<Student> findByContractEndDateBefore(Date date);
  List<Student> findByContractEndDateBefore(LocalDate date );
    List<Student> findByRoom(Room room);

}
