package com.Hostel_Management.hostel.Services;

import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.RoomType;
import com.Hostel_Management.hostel.Repository.RoomRepository;
import com.Hostel_Management.hostel.Repository.RoomTypeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepo;
    private final RoomTypeRepository roomTypeRepo;

    public RoomService(RoomRepository roomRepo, RoomTypeRepository roomTypeRepo) {
        this.roomRepo = roomRepo;
        this.roomTypeRepo = roomTypeRepo;
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }



    // Get room by type
    public List<Room> getRoomsByType(Long typeId) {
        RoomType roomType = roomTypeRepo.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Room type not found"));
        return roomRepo.findByRoomType(roomType);
    }

    // Get room by ID
    public Optional<Room> getRoomById(Long roomId) {
        return roomRepo.findById(roomId);
    }


    // Update room
    public Room updateRoom(Long roomId, Room updatedRoom) {
        return roomRepo.findById(roomId).map(room -> {
            // Update current occupancy
            room.setCurrOccu(updatedRoom.getCurrOccu());


            return roomRepo.save(room);
        }).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    // Create room
//    public Room createRoom(Room room) {
//        // Ensure roomType exists
//        //int RoomId = room.
//        if (room.getRoomType() == null) {
//            throw new RuntimeException("Room type must be provided");
//        }
//        //RoomType roomType = roomTypeRepo.findById(room.getRoomType().getTypeId());
//       // .orElseThrow(() -> new RuntimeException("Room type not found"));
//
//       // RoomType roomType = roomTypeRepo.findById(room.getRoomType().getTypeId());
//        //        .orElseThrow(() -> new RuntimeException("Room type not found"));
//       // room.setRoomType(roomType);
//
//        return roomRepo.save(room);
//    }
// Create room
    public Room createRoom(Room room) {
        if (room.getRoomType() == null || room.getRoomType().getTypeId() == null) {
            throw new RuntimeException("Room type must be provided");
        }

        // Validate RoomType exists
        RoomType roomType = roomTypeRepo.findById(room.getRoomType().getTypeId())
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        // Attach managed RoomType to Room
        room.setRoomType(roomType);

        // Save Room
        return roomRepo.save(room);
    }

    // Delete room
    public void deleteRoom(Long roomId) {
        if (!roomRepo.existsById(roomId)) {
            throw new RuntimeException("Room not found");
        }
        roomRepo.deleteById(roomId);
    }
    // Create RoomType
    public RoomType createRoomType(RoomType roomType) {
        return roomTypeRepo.save(roomType);
    }

    // Update RoomType
    public RoomType updateRoomType(Long typeId, RoomType updatedRoomType) {
        return roomTypeRepo.findById(typeId).map(rt -> {
            rt.setFees(updatedRoomType.getFees());
            rt.setSize(updatedRoomType.getSize());
            return roomTypeRepo.save(rt);
        }).orElseThrow(() -> new RuntimeException("Room type not found"));
    }

    // Delete RoomType
    public void deleteRoomType(Long typeId) {
        if (!roomTypeRepo.existsById(typeId)) {
            throw new RuntimeException("Room type not found");
        }
        roomTypeRepo.deleteById(typeId);
    }
    //public void allocateRoom()

}
