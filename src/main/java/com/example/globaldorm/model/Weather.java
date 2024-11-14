package com.example.globaldorm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="weather")
public class Weather {
    @Id
    private String id;
    private String product;
    private String init;
    private String lat;
    private String lon;
    private List<WeatherData> dataseries;
}
