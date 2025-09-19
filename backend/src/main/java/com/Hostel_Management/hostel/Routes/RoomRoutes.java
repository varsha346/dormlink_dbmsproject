package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Services.RoomService;
import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.models.RoomType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomRoutes {

    private final RoomService roomService;

    public RoomRoutes(RoomService roomService) {
        this.roomService = roomService;
    }

    // 🔹 Get all rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // 🔹 Get room by ID
    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Get rooms by student ID
    @GetMapping("/student/{studentId}")
    public List<Room> getRoomsByStudentId(@PathVariable Long studentId) {
        return roomService.getRoomsByStudentId(studentId);
    }

    // 🔹 Get rooms by type
    @GetMapping("/type/{typeId}")
    public List<Room> getRoomsByType(@PathVariable Long typeId) {
        return roomService.getRoomsByType(typeId);
    }

    // 🔹 Create room
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    // 🔹 Update room
    @PutMapping("/{roomId}")
    public Room updateRoom(@PathVariable Long roomId, @RequestBody Room updatedRoom) {
        return roomService.updateRoom(roomId, updatedRoom);
    }

    // 🔹 Delete room
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }

    // ---------------- RoomType APIs ----------------

    // 🔹 Create RoomType
    @PostMapping("/types")
    public RoomType createRoomType(@RequestBody RoomType roomType) {
        return roomService.createRoomType(roomType);
    }

    // 🔹 Update RoomType
    @PutMapping("/types/{typeId}")
    public RoomType updateRoomType(@PathVariable Long typeId, @RequestBody RoomType updatedRoomType) {
        return roomService.updateRoomType(typeId, updatedRoomType);
    }

    // 🔹 Delete RoomType
    @DeleteMapping("/types/{typeId}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable Long typeId) {
        roomService.deleteRoomType(typeId);
        return ResponseEntity.noContent().build();
    }
}
