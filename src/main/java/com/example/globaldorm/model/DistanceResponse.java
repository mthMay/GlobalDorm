package com.example.globaldorm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceResponse {
    private double distance;
    private double duration;

    public DistanceResponse() {}

    public DistanceResponse(double distance, double duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double getDuration() {
        return duration;
    }
    public void setDuration(double duration) {
        this.duration = duration;
    }
}
