package com.example.globaldorm.controller;

import com.example.globaldorm.model.RoomApplication;
import com.example.globaldorm.service.RoomApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class RoomApplicationController {

    private RoomApplicationService roomApplicationService;

    public RoomApplicationController(RoomApplicationService roomApplicationService) {
        this.roomApplicationService = roomApplicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyForRoom (@RequestBody RoomApplication roomApplication) {
        try {
            RoomApplication savedApplication = roomApplicationService.applyForRoom(roomApplication);
            return ResponseEntity.ok(savedApplication);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/cancelled/{id}")
    public ResponseEntity<RoomApplication> cancelApplication (@PathVariable String id) {
        RoomApplication cancelledApplication = roomApplicationService.cancelApplication(id);
        return ResponseEntity.ok(cancelledApplication);
    }

    @PatchMapping("/accepted/{id}")
    public ResponseEntity<RoomApplication> acceptApplication (@PathVariable String id) {
        RoomApplication acceptedApplication = roomApplicationService.acceptApplication(id);
        return ResponseEntity.ok(acceptedApplication);
    }

    @PatchMapping("/rejected/{id}")
    public ResponseEntity<RoomApplication> rejectApplication (@PathVariable String id) {
        RoomApplication rejectedApplication = roomApplicationService.rejectApplication(id);
        return ResponseEntity.ok(rejectedApplication);
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<RoomApplication>> getApplicationsByApplicantId(@PathVariable String applicantId) {
        return ResponseEntity.ok(roomApplicationService.getApplicationByApplicationId(applicantId));
    }
}
