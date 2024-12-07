package com.example.globaldorm.controller;

import com.example.globaldorm.model.Room;
import com.example.globaldorm.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/search")
    public List<Room> searchRooms(
            @RequestParam String city) {
        return roomService.searchAvailableRooms(city);
    }
}
