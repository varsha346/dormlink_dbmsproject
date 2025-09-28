package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.RoomReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomReview, Long> {
    // Additional custom queries if needed
}
