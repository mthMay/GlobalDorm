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

    @PatchMapping("/accept/{id}")
    public ResponseEntity<RoomApplication> acceptApplication (@PathVariable String id) {
        RoomApplication acceptedApplication = roomApplicationService.acceptApplication(id);
        return ResponseEntity.ok(acceptedApplication);
    }

    @PatchMapping("/reject/{id}")
    public ResponseEntity<RoomApplication> rejectApplication (@PathVariable String id) {
        RoomApplication rejectedApplication = roomApplicationService.rejectApplication(id);
        return ResponseEntity.ok(rejectedApplication);
    }
}
