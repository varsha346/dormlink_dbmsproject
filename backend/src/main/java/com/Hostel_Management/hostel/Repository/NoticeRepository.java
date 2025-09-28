package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice , Long>{

}
