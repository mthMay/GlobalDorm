package com.example.globaldorm.controller;

import com.example.globaldorm.model.RoomApplication;
import com.example.globaldorm.service.RoomApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/applications")
public class RoomApplicationController {

    @Autowired
    private RoomApplicationService roomApplicationService;

    @PostMapping("/apply")
    public ResponseEntity<RoomApplication> applyForRoom (@RequestBody RoomApplication roomApplication) {
        RoomApplication savedApplication = roomApplicationService.applyForRoom(roomApplication);
        return ResponseEntity.ok(savedApplication);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<RoomApplication> cancelApplication (@PathVariable String id) {
        RoomApplication cancelledApplication = roomApplicationService.cancelApplication(id);
        return ResponseEntity.ok(cancelledApplication);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApplication (@PathVariable String id) {
        try{
            roomApplicationService.deleteApplication(id);
            return ResponseEntity.ok("Your application has been successfully deleted.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
