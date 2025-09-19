package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    // Get all rooms by RoomType
    List<Room> findByRoomType(RoomType roomType);

    // Example: Get room by student id (assuming Allocation entity links Student and Room)

    List<Room> findByAllocations_Student_StuId(Long stuId);

}
