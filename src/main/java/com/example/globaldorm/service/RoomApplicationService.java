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
        return roomApplicationRepository.save(roomApplication);
    }

    public void cancelApplication (String applicationId) {
        if (roomApplicationRepository.existsById(applicationId)) {
            roomApplicationRepository.deleteById(applicationId);
        } else {
            throw new IllegalArgumentException("Application id " + applicationId + " does not exist");
        }
    }
}
