package com.example.globaldorm.controller;

import com.example.globaldorm.model.Room;
import com.example.globaldorm.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.185:3000"})
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Optional<Room> findRoomById(
            @PathVariable String id) {
        return roomService.findRoomsById(id);
    }

    @GetMapping("/search")
    public List<Room> searchRoomsByCity(
            @RequestParam String city) {
        return roomService.searchRoomsByCity(city);
    }
}
