package com.Hostel_Management.hostel.Routes;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Map;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.Repository.RoomRepository;
import com.Hostel_Management.hostel.Repository.PaymentRepository;

@RestController
@RequestMapping("/api/payment")
public class AfterPaymentRoute {

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpaySecret;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    @PostMapping("/success")
    public ResponseEntity<?> handlePaymentSuccess(@RequestBody Map<String, Object> data) {
        try {
            Long studentId = Long.valueOf(data.get("studentId").toString());
            Long roomNo = Long.valueOf(data.get("roomNo").toString());

            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            Room room = roomRepository.findById(roomNo)
                    .orElseThrow(() -> new RuntimeException("Room not found"));

            if (room.getCurrOccu() >= room.getSize()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Room is full"));
            }

            // Assign student and increment occupancy atomically
            student.setRoom(room);
            student.setFeeStatus(true);
            student.setContractEndDate(LocalDate.now().plusYears(1));
            studentRepository.save(student);

            room.setCurrOccu(room.getCurrOccu() + 1);
            roomRepository.save(room);

            // Save payment record
            Room.Payment payment = new Room.Payment();
            payment.setOrderId((String) data.get("razorpay_order_id"));
            payment.setPaymentId((String) data.get("razorpay_payment_id"));
            payment.setAmountPaid((int) data.get("amount"));
            payment.setStudent(student);
            payment.setDate(LocalDate.now());
            paymentRepository.save(payment);

            return ResponseEntity.ok(Map.of("message", "Payment processed and student assigned."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }


}
