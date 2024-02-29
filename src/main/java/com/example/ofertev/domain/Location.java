package com.example.ofertev.domain;

public class Location {
    private Double locationId;
    private String locationName;

    public Location(Double locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public Double getLocationId() {
        return locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public String toString() {
        return locationName;
    }
}
