package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.models.Student;
import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.Allocation;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.Repository.RoomRepository;
import com.Hostel_Management.hostel.Repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.Date;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomAllocationScheduler {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AllocationRepository allocationRepository;

    // Runs daily at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void releaseExpiredRoomAllocations() {
//        LocalDate today = LocalDate.now();
//
//        // Find students whose contract has ended
//        List<Student> expiredStudents = studentRepository.findByContractEndDateBefore(today);
        LocalDate today = LocalDate.now();
        //java.sql.Date sqlToday = java.sql.Date.valueOf(today);

        List<Student> expiredStudents = studentRepository.findByContractEndDateBefore(today);


        for (Student student : expiredStudents) {
            Room room = student.getRoom();
            if (room != null) {

                // Save allocation history
                Allocation allocation = new Allocation();
                allocation.setStudent(student);
                allocation.setRoom(room);
                allocation.setStartDate(student.getContractEndDate().minusYears(1)); // Assuming 1-year contract
                allocation.setEndDate(student.getContractEndDate());
                allocationRepository.save(allocation);

                // Free the room
                room.setCurrOccu(room.getCurrOccu() - 1);
                roomRepository.save(room);

                // Remove room allocation from student
                student.setRoom(null);
                studentRepository.save(student);
            }
        }
    }
}