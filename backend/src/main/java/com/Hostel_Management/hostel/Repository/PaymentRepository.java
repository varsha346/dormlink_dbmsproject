package com.Hostel_Management.hostel.Repository;

import com.Hostel_Management.hostel.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
