package com.example.globaldorm.repository;

import com.example.globaldorm.model.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {
    Optional<Weather> findByLatAndLon(String lat, String lon);}
