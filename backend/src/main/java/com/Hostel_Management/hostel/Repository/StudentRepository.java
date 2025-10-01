package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  //  List<Student> findByContractEndDateBefore(Date date);
  List<Student> findByContractEndDateBefore(LocalDate date );
    List<Student> findByRoom(Room room);
    List<Student> findByContractEndDateAfter(LocalDate date);
    // ✅ Current allocations filter
    @Query("SELECT s FROM Student s " +
            "WHERE s.contractEndDate >= :today " +
            "AND (:year IS NULL OR YEAR(s.contractStartDate) = :year) " +
            "AND (:roomNo IS NULL OR s.room.roomNo = :roomNo) " +
            "AND (:studentName IS NULL OR LOWER(s.user.name) LIKE LOWER(CONCAT('%', :studentName, '%')))")
    List<Student> filterCurrentAllocations(@Param("today") LocalDate today,
                                           @Param("year") Integer year,
                                           @Param("roomNo") String roomNo,
                                           @Param("studentName") String studentName);
    //List<Student> findByContractEndDateBefore(LocalDate date);

}
