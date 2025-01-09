package com.example.globaldorm.service;

import com.example.globaldorm.model.RoomApplication;
import com.example.globaldorm.repository.RoomApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomApplicationService {

    private RoomApplicationRepository roomApplicationRepository;

    public RoomApplicationService(RoomApplicationRepository roomApplicationRepository) {
        this.roomApplicationRepository = roomApplicationRepository;
    }

    public RoomApplication applyForRoom (RoomApplication roomApplication) {
        boolean applicationExist = roomApplicationRepository.existsByRoomIdAndApplicantIdAndStatusNot(
                roomApplication.getRoomId(), roomApplication.getApplicantId(), "CANCELLED");
        if (applicationExist) {
            throw new IllegalArgumentException("You have already applied for this room.");
        }
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

    public RoomApplication acceptApplication(String applicationId) {
        RoomApplication application = roomApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application with ID " + applicationId + " does not exist."));
        application.setStatus("ACCEPTED");
        return roomApplicationRepository.save(application);
    }

    public RoomApplication rejectApplication(String applicationId) {
        RoomApplication application = roomApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application with ID " + applicationId + " does not exist."));
        application.setStatus("REJECTED");
        return roomApplicationRepository.save(application);
    }

    public List<RoomApplication> getApplicationByApplicationId(String applicantId) {
        return roomApplicationRepository.findByApplicantId(applicantId);
    }
}
