package com.example.globaldorm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roomApplications")
public class RoomApplication {
    @Id
    private String id;
    private String roomId;
    private String applicantName;
    private String applicantEmail;
    private String status = "PENDING";
}
