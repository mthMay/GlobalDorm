package com.example.globaldorm.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private String product;
    private String init;
    @SerializedName("dataseries")
    private List<WeatherData> dataSeries;
}
