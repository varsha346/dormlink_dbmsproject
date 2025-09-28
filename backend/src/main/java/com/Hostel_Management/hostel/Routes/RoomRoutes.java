//package com.Hostel_Management.hostel.Routes;
//
//import com.Hostel_Management.hostel.Services.RoomService;
//import com.Hostel_Management.hostel.models.Room;
//import com.Hostel_Management.hostel.models.RoomReview;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rooms")
//public class RoomRoutes {
//
//    private final RoomService roomService;
//
//    public RoomRoutes(RoomService roomService) {
//        this.roomService = roomService;
//    }
//
//    // 🔹 Get all rooms
//    @GetMapping
//    public List<Room> getAllRooms() {
//        return roomService.getAllRooms();
//    }
//
//    // 🔹 Get room by ID
//    @GetMapping("/{roomId}")
//    public ResponseEntity<Room> getRoomById(@PathVariable Long roomId) {
//        return roomService.getRoomById(roomId)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//
//
//    // 🔹 Get rooms by type
//    @GetMapping("/type/{typeId}")
//    public List<Room> getRoomsByType(@PathVariable Long typeId) {
//        return roomService.getRoomsByType(typeId);
//    }
//
//    // 🔹 Create room
//    @PostMapping
//    public Room createRoom(@RequestBody Room room) {
//        return roomService.createRoom(room);
//    }
//
//    // 🔹 Update room
//    @PutMapping("/{roomId}")
//    public Room updateRoom(@PathVariable Long roomId, @RequestBody Room updatedRoom) {
//        return roomService.updateRoom(roomId, updatedRoom);
//    }
//
//    // 🔹 Delete room
//    @DeleteMapping("/{roomId}")
//    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
//        roomService.deleteRoom(roomId);
//        return ResponseEntity.noContent().build();
//    }
//
//    // ---------------- RoomType APIs ----------------
//
//    // 🔹 Create RoomType
//    @PostMapping("/types")
//    public RoomReview createRoomType(@RequestBody RoomReview roomType) {
//        return roomService.createRoomType(roomType);
//    }
//
//    // 🔹 Update RoomType
//    @PutMapping("/types/{typeId}")
//    public RoomReview updateRoomType(@PathVariable Long typeId, @RequestBody RoomReview updatedRoomType) {
//        return roomService.updateRoomType(typeId, updatedRoomType);
//    }
//
//    // 🔹 Delete RoomType
//    @DeleteMapping("/types/{typeId}")
//    public ResponseEntity<Void> deleteRoomType(@PathVariable Long typeId) {
//        roomService.deleteRoomType(typeId);
//        return ResponseEntity.noContent().build();
//    }
//}
package com.Hostel_Management.hostel.Routes;

import com.Hostel_Management.hostel.Dto.RoomResponseDTO;
import com.Hostel_Management.hostel.models.Room;
import com.Hostel_Management.hostel.Services.RoomService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomRoutes {

    private final RoomService roomService;

    public RoomRoutes(RoomService roomService) {
        this.roomService = roomService;
    }

    // ===============================
    // 1️⃣ Create a new room
    // POST /rooms
    // Request body: Room JSON
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    // ===============================
    // 2️⃣ Delete a room by roomNo
    // DELETE /rooms/{roomNo}
    @DeleteMapping("/{roomNo}")
    public void deleteRoom(@PathVariable Long roomNo) {
        roomService.deleteRoom(roomNo);
    }

    // ===============================
    // 3️⃣ Get rooms (student or warden view)
    // GET /rooms-view?validDate=2025-09-28&showAll=false
    // - validDate: Optional. Filters students by contractEndDate >= validDate. Defaults to today.
    // - showAll: Optional. false = student view (only unoccupied rooms)
    //                     true = warden view (all rooms)
    @GetMapping("/rooms-view")
    public List<RoomResponseDTO> getRooms(
            @RequestParam(required = false) LocalDate validDate,
            @RequestParam(defaultValue = "false") boolean showAll
    ) {
        return roomService.getRooms(validDate, showAll);
    }

    // ===============================
//    // 4️⃣ Optional: Get a single room by roomNo (with reviews + allocated students)
//    // GET /rooms/{roomNo}?validDate=2025-09-28
//    @GetMapping("/{roomNo}")
//    public RoomResponseDTO getRoomById(
//            @PathVariable Long roomNo,
//            @RequestParam(required = false) LocalDate validDate
//    ) {
//        if (validDate == null) validDate = LocalDate.now();
//        return roomService.getRooms(validDate, true).stream()
//                .filter(roomDTO -> roomDTO.getRoomNo().equals(roomNo))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Room not found"));
//    }
}
