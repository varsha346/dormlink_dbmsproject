
package com.Hostel_Management.hostel.Routes;

import org.springframework.web.bind.annotation.*;
        import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.Map;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.Payment;
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

    @PostMapping("/success")
    public ResponseEntity<?> handlePaymentSuccess(@RequestBody Map<String, Object> data) {
        try {
            String razorpayOrderId = (String) data.get("razorpay_order_id");
            String razorpayPaymentId = (String) data.get("razorpay_payment_id");
            Long studentId = (Long) data.get("studentId");
            Long roomNo = (Long) data.get("roomNo");
            int amount = (int) data.get("amount"); // Should be passed from frontend

            // 1. Validate student and room
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            Room room = roomRepository.findById(roomNo)
                    .orElseThrow(() -> new RuntimeException("Room not found"));

            // 2. Update student info
            student.setFeeStatus(true);
            //student.setRoom(roomNo);
            student.setContractEndDate(LocalDate.now().plusYears(1));
            studentRepository.save(student);

            // 3. Update room occupancy
            room.setCurrOccu(room.getCurrOccu() + 1);
            roomRepository.save(room);

            // 4. Save payment record
            Payment payment = new Payment();
            payment.setOrderId(razorpayOrderId);
            payment.setPaymentId(razorpayPaymentId);
            payment.setAmountPaid(amount);
            payment.setStudent(student);
            payment.setDate(LocalDate.now());

            paymentRepository.save(payment);

            return ResponseEntity.ok(Map.of("message", "Payment processed and student updated."));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
