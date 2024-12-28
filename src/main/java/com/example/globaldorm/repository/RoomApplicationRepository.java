package com.example.globaldorm.repository;

import com.example.globaldorm.model.RoomApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomApplicationRepository extends MongoRepository<RoomApplication, String> {
    List<RoomApplication> findByApplicantId(String applicantId);
    boolean existsByRoomIdAndApplicantId(String roomId, String applicantId);
}
