package com.example.globaldorm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;
import java.util.List;

@Data
@Document(collection = "rooms")
public class Room {
    @Id
    private String id;
    private String name;
    private Address address;
    private RoomDetails details;
    @Field("price_per_month_gbp")
    private double pricePerMonthGbp;
    @Field("availability_date")
    private Date availabilityDate;
    @Field("spoken_languages")
    private List<String> spokenLanguages;

    public Room() {}

    public Room(String id, String name, Address address, RoomDetails details, double pricePerMonthGbp, Date availabilityDate, List<String> spokenLanguages) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.details = details;
        this.pricePerMonthGbp = pricePerMonthGbp;
        this.availabilityDate = availabilityDate;
        this.spokenLanguages = spokenLanguages;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public RoomDetails getDetails() {
        return details;
    }
    public void setDetails(RoomDetails details) {
        this.details = details;
    }
    public double getPricePerMonthGbp() {
        return pricePerMonthGbp;
    }
    public void setPricePerMonthGbp(double pricePerMonthGbp) {
        this.pricePerMonthGbp = pricePerMonthGbp;
    }
    public Date getAvailabilityDate() {
        return availabilityDate;
    }
    public void setAvailabilityDate(Date availabilityDate) {
        this.availabilityDate = availabilityDate;
    }
    public List<String> getSpokenLanguages() {
        return spokenLanguages;
    }
    public void setSpokenLanguages(List<String> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }
}
