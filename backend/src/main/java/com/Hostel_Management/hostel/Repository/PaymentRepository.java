package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // You can add custom query methods here if needed later
}
