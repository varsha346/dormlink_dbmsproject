package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.Dto.RoomResponseDTO;
import com.Hostel_Management.hostel.Repository.RoomRepository;
import com.Hostel_Management.hostel.Repository.StudentRepository;
import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepo;
    private final StudentRepository studentRepo;

    public RoomService(RoomRepository roomRepo, StudentRepository studentRepo) {
        this.roomRepo = roomRepo;
        this.studentRepo = studentRepo;
    }

    // ✅ Unified method for available/all rooms
    public List<RoomResponseDTO> getRooms(final LocalDate validDate, boolean showAll) {
       // if (validDate == null) validDate = LocalDate.now();

        List<Room> rooms = roomRepo.findAll();

        return rooms.stream()
                // If showAll = false (student view), filter only rooms with currOccu < size
                .filter(room -> showAll || room.getCurrOccu() < room.getSize())
                .map(room -> {
                    // Get students whose contractEndDate >= validDate
                    List<Student> validStudents = studentRepo.findByRoom(room).stream()
                            .filter(student -> student.getContractEndDate() != null &&
                                    !student.getContractEndDate().isBefore(validDate))
                            .collect(Collectors.toList());

                    return new RoomResponseDTO(room, validStudents);
                })
                .collect(Collectors.toList());
    }

    // ✅ Create room
    public Room createRoom(Room room) {
        return roomRepo.save(room);
    }

    // ✅ Delete room
    public void deleteRoom(Long roomNo) {
        if (!roomRepo.existsById(roomNo)) {
            throw new RuntimeException("Room not found");
        }
        roomRepo.deleteById(roomNo);
    }
}