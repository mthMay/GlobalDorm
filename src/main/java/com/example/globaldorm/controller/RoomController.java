package com.example.globaldorm.controller;

import com.example.globaldorm.model.Room;
import com.example.globaldorm.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

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
