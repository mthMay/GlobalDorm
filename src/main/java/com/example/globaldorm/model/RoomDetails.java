package com.example.globaldorm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDetails {
    private boolean furnished;
    private List<String> amenities;
    @Field("live_in_landlord")
    private boolean liveInLandlord;
    @Field("shared_with")
    private int sharedWith;
    @Field("bills_included")
    private boolean billsIncluded;
    @Field("bathroom_shared")
    private boolean bathroomShared;
}
