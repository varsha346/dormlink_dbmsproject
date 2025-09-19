package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    // Additional custom queries if needed
}
