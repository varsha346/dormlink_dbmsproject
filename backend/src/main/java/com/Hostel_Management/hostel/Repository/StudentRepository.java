package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  //  List<Student> findByContractEndDateBefore(Date date);
  List<Student> findByContractEndDateBefore(LocalDate date );
    List<Student> findByRoom(Room room);
    List<Student> findByContractEndDateAfter(LocalDate date);
    @Query("SELECT s FROM Student s " +
            "WHERE (:today IS NULL OR s.contractEndDate >= :today) " +
            "AND (:year IS NULL OR s.year = :year) " +
            "AND (:roomNo IS NULL OR s.room.roomNo = :roomNo) " +
            "AND (:studentName IS NULL OR LOWER(s.user.name) LIKE LOWER(CONCAT('%', :studentName, '%')))")
    List<Student> filterCurrentAllocations(@Param("today") LocalDate today,
                                           @Param("year") String year,
                                           @Param("roomNo") String roomNo,
                                           @Param("studentName") String studentName);


}
