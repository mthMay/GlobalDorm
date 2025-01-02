package com.example.globaldorm.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
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

    public RoomDetails() {}

    public RoomDetails(boolean furnished, List<String> amenities, boolean LiveInLandlord, int sharedWith, boolean billsIncluded, boolean bathroomShared) {
        this.furnished = furnished;
        this.amenities = amenities;
        this.liveInLandlord = LiveInLandlord;
        this.sharedWith = sharedWith;
        this.billsIncluded = billsIncluded;
        this.bathroomShared = bathroomShared;
    }

    public boolean isFurnished() {
        return furnished;
    }
    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }
    public List<String> getAmenities() {
        return amenities;
    }
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
    public boolean isLiveInLandlord() {
        return liveInLandlord;
    }
    public void setLiveInLandlord(boolean liveInLandlord) {
        this.liveInLandlord = liveInLandlord;
    }
    public int getSharedWith() {
        return sharedWith;
    }
    public void setSharedWith(int sharedWith) {
        this.sharedWith = sharedWith;
    }
    public boolean isBillsIncluded() {
        return billsIncluded;
    }
    public void setBillsIncluded(boolean billsIncluded) {
        this.billsIncluded = billsIncluded;
    }
    public boolean isBathroomShared() {
        return bathroomShared;
    }
    public void setBathroomShared(boolean bathroomShared) {
        this.bathroomShared = bathroomShared;
    }
}
