package com.example.globaldorm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCode {
    private String postcode;
    private double latitude;
    private double longitude;

    public GeoCode() {}

    public GeoCode(String postcode, double latitude, double longitude) {
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}