package com.example.globaldorm.repository;

import com.example.globaldorm.model.RoomApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomApplicationRepository extends MongoRepository<RoomApplication, String> {
}
