package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    List<Allocation> findByStudent_StuId(Long studentId);

    @Query("SELECT a FROM Allocation a " +
            "WHERE (:year IS NULL OR YEAR(a.startDate) = :year) " +
            "AND (:roomNo IS NULL OR a.room.roomNo = :roomNo) " +
            "AND (:studentName IS NULL OR LOWER(a.student.user.name) LIKE LOWER(CONCAT('%', :studentName, '%')))")
    List<Allocation> filterAllocations(@Param("year") Integer year,
                                       @Param("roomNo") String roomNo,
                                       @Param("studentName") String studentName);

}
