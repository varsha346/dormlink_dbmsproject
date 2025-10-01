package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.Repository.RoomRepository;
import com.Hostel_Management.hostel.Repository.PaymentRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import java.util.HashMap;
import java.util.Map;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

@RestController
@RequestMapping("/api/payment")
public class PaymentRoute {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpaySecret;

    // Create order endpoint
    @PostMapping("/create-order")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
        Integer amountInt = (Integer) data.get("amount");
        if (amountInt == null) {
            amountInt = 10000; // default ₹100 if amount not sent
        }

        RazorpayClient client = new RazorpayClient(razorpayKeyId, razorpaySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInt); // in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("payment_capture", 1);

        Order order = client.Orders.create(orderRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("id", order.get("id"));
        response.put("currency", order.get("currency"));
        response.put("amount", order.get("amount"));
        response.put("key", razorpayKeyId);

        return ResponseEntity.ok(response);
    }

}
