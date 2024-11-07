package com.example.globaldorm.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private long date;
    private String weather;
    @SerializedName("temp2m")
    private Temp2M temp2M;
    @SerializedName("wind10m_max")
    private long wind10MMax;
}
