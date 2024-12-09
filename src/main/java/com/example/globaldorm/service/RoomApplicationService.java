package com.example.globaldorm.service;

import com.example.globaldorm.model.RoomApplication;
import com.example.globaldorm.repository.RoomApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomApplicationService {

    @Autowired
    private RoomApplicationRepository roomApplicationRepository;

    public RoomApplication applyForRoom (RoomApplication roomApplication) {
        if (roomApplication.getStatus() == null || roomApplication.getStatus().isEmpty()) {
            roomApplication.setStatus("PENDING");
        }
        return roomApplicationRepository.save(roomApplication);
    }

    public RoomApplication cancelApplication(String applicationId) {
        RoomApplication application = roomApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application with ID " + applicationId + " does not exist."));
        application.setStatus("CANCELLED");
        return roomApplicationRepository.save(application);
    }

    public void deleteApplication (String applicationId) {
        if (roomApplicationRepository.existsById(applicationId)) {
            roomApplicationRepository.deleteById(applicationId);
        } else {
            throw new IllegalArgumentException("Application id " + applicationId + " does not exist");
        }
    }
}
