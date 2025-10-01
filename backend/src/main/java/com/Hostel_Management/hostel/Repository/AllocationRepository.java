package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.Hostel_Management.hostel.Dto.AllocationDTO;

import java.util.List;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    List<Allocation> findByStudent_StuId(Long studentId);

    @Query("SELECT new com.Hostel_Management.hostel.dto.AllocationDTO(" +
            "a.student.stuId, a.student.user.name, a.student.year, a.student.contractEndDate, a.room.roomNo, a.startDate, a.endDate) " +
            "FROM Allocation a " +
            "WHERE (:year IS NULL OR YEAR(a.startDate) = :year) " +
            "AND (:roomNo IS NULL OR a.room.roomNo = :roomNo) " +
            "AND (:studentName IS NULL OR LOWER(a.student.user.name) LIKE LOWER(CONCAT('%', :studentName, '%')))")
    List<AllocationDTO> filterAllocations(@Param("year") Integer year,
                                          @Param("roomNo") String roomNo,
                                          @Param("studentName") String studentName);

}
