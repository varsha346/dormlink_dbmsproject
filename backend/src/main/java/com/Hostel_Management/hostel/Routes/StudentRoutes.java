package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Services.StudentService;
import com.Hostel_Management.hostel.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentRoutes {

    private final StudentService studentService;

    // ✅ Get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // ✅ Get student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    // ✅ Allocate room
    @PostMapping("/{studentId}/allocate/{roomId}")
    public ResponseEntity<String> allocateRoom(
            @PathVariable Long studentId,
            @PathVariable Long roomId) {
        studentService.allocateRoom(studentId, roomId);
        return ResponseEntity.ok("Room allocated successfully");
    }

    // ✅ Deallocate room
    @PostMapping("/{studentId}/deallocate")
    public ResponseEntity<String> deallocateRoom(@PathVariable Long studentId) {
        studentService.deallocateRoom(studentId);
        return ResponseEntity.ok("Room deallocated successfully");
    }

    // ✅ Update profile
    @PutMapping("/{stuId}/profile")
    public ResponseEntity<Student> updateProfile(
            @PathVariable Long stuId,
            @RequestBody Student updatedData) {
        return ResponseEntity.ok(studentService.updateProfile(stuId, updatedData));
    }

    // ✅ Get student profile by ID
    @GetMapping("/{stuId}/profile")
    public Map<String, Object> getStudentProfile(@PathVariable Long stuId) {
        return studentService.getProfile(stuId);
    }
}
