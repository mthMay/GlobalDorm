package com.example.globaldorm.model;

import lombok.Data;

@Data
public class Address {
    private String city;
    private String county;
    private String postcode;

    public Address() {}

    public Address(String city, String county, String postcode) {
        this.city = city;
        this.county = county;
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
