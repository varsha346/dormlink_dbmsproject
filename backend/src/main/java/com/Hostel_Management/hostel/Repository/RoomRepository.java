package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.RoomReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {



}
