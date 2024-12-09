package com.example.globaldorm.controller;

import com.example.globaldorm.model.RoomApplication;
import com.example.globaldorm.service.RoomApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class RoomApplicationController {

    @Autowired
    private RoomApplicationService roomApplicationService;

    @PostMapping("/apply")
    public ResponseEntity<RoomApplication> applyForRoom (@RequestBody RoomApplication roomApplication) {
        RoomApplication savedApplication = roomApplicationService.applyForRoom(roomApplication);
        return ResponseEntity.ok(savedApplication);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<String> cancelApplication (@PathVariable String id) {
        try{
            roomApplicationService.cancelApplication(id);
            return ResponseEntity.ok("Application with ID " + id + " has been successfully withdrawn.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
