package com.example.globaldorm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roomApplications")
public class RoomApplication {
    @Id
    private String id;
    private String roomId;
    private String applicantId;
    private String status = "PENDING";

    public RoomApplication() {}

    public RoomApplication(String id, String roomId, String applicantId, String status) {
        this.id = id;
        this.roomId = roomId;
        this.applicantId = applicantId;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getRoomId() {
        return roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    public String getApplicantId() {
        return applicantId;
    }
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
