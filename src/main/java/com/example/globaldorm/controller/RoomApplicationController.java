package com.example.globaldorm.controller;

import com.example.globaldorm.model.RoomApplication;
import com.example.globaldorm.service.RoomApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
