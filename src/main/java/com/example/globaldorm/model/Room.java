package com.example.globaldorm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rooms")
public class Room {
    @Id
    private String _id;
    private int id;
    private String name;
    private Address address;
    private RoomDetails details;
    @Field("price_per_month_gbp")
    private double pricePerMonthGbp;
    @Field("availability_date")
    private Date availabilityDate;
    @Field("spoken_languages")
    private List<String> spokenLanguages;
}
