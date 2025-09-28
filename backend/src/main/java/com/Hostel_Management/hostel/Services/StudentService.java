package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.Repository.RoomRepository;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.Repository.UserRepo;
import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.Hostel_Management.hostel.Dto.StudentDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepo;
    private final RoomRepository roomRepo;
    private final UserRepo userRepo;

    // ✅ Get all students
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // ✅ Get student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }


//    // ✅ Allocate room to student
//    @Transactional
//    public void allocateRoom(Long studentId, Long roomId) {
//        Student student = studentRepo.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        if (student.getRoom() != null) {
//            throw new RuntimeException("Student already has a room allocated");
//        }
//
//        Room room = roomRepo.findById(roomId)
//                .orElseThrow(() -> new RuntimeException("Room not found"));
//
//        if (room.getCurrOccu() >= room.getRoomType().getSize()) {
//            throw new RuntimeException("Room is already full");
//        }
//
//        student.setRoom(room);
//        room.setCurrOccu(room.getCurrOccu() + 1);
//
//        studentRepo.save(student);
//        roomRepo.save(room);
//    }
//
//    // ✅ Deallocate room
//    @Transactional
//    public void deallocateRoom(Long studentId) {
//        Student student = studentRepo.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        Room room = student.getRoom();
//        if (room == null) {
//            throw new RuntimeException("Student has no allocated room");
//        }
//
//        if (room.getCurrOccu() > 0) {
//            room.setCurrOccu(room.getCurrOccu() - 1);
//        }
//
//        student.setRoom(null);
//
//        studentRepo.save(student);
//        roomRepo.save(room);
//    }
//
//    // ✅ Update student profile
//
//    @Transactional
//    public Student updateProfile(Long stuId, StudentDto dto) {
//        User user = userRepo.findById(stuId)
//                .orElseThrow(() -> new RuntimeException("User not found with id " + stuId));
//
//        Student student = studentRepo.findById(stuId)
//                .orElseGet(() -> {
//                    Student s = new Student();
//                    s.setStuId(user.getUserId());
//                    s.setUser(user);
//                    return s;
//                });
//
//        // ✅ Update student fields
//        if (dto.getContact() != null) student.setContact(dto.getContact());
//        if (dto.getGuardianContact() != null) student.setGuardianContact(dto.getGuardianContact());
//        if (dto.getDept() != null) student.setDept(dto.getDept());
//        if (dto.getAddress() != null) student.setAddress(dto.getAddress());
//        if (dto.getYear() != null) student.setYear(dto.getYear());
//        student.setFeeStatus(false);
//
//        // ✅ Update user fields
//        if (dto.getName() != null) user.setName(dto.getName());
//        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
//
//        userRepo.save(user);
//        return studentRepo.save(student);
//    }


    //    // ✅ Get student profile
//    public Student getProfile(Long stuId) {
//        return studentRepo.findById(stuId)
//                .orElseThrow(() -> new RuntimeException("Student not found with id " + stuId));
//    }
//}
    public Map<String, Object> getProfile(Long stuId) {
        Student student = studentRepo.findById(stuId)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + stuId));

        Map<String, Object> profile = new HashMap<>();

        // Student fields
        profile.put("stuId", student.getStuId());
        profile.put("dept",student.getDept());
        profile.put("contact",student.getContact());
        profile.put("address",student.getAddress());
        profile.put("year",student.getYear());
        profile.put("guardianContact",student.getGuardianContact());



        // profile.put("branch", student.getBranch());         // example field
        // profile.put("year", student.getYear());             // example field
        // profile.put("roomNumber", student.getRoomNumber()); // example field

        // User fields
        profile.put("name", student.getUser().getName());
        profile.put("email", student.getUser().getEmail());
        //profile.put("password", student.getUser().getPassword()); // hashed, be careful
        //String decodedPassword = PasswordUtil.decrypt(student.getUser().getPassword());
        //profile.put("password", decodedPassword);
        // Add more fields if needed
        // profile.put("fieldName", student.getField());

        return profile;
    }



}