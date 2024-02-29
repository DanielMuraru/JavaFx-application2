package com.example.ofertev.domain;

import com.example.ofertev.utils.Types;

public class Hotel {
    private Double hotelID;
    private Double locationId;
    private String hotelName;
    private int noRooms;
    private double pricePerNight;
    private Types type;

    public Hotel(Double hotelID, Double locationId, String hotelName, int noRooms, double pricePerNight, Types type) {
        this.hotelID = hotelID;
        this.locationId = locationId;
        this.hotelName = hotelName;
        this.noRooms = noRooms;
        this.pricePerNight = pricePerNight;
        this.type = type;
    }

    public Double getHotelID() {
        return hotelID;
    }

    public Double getLocationId() {
        return locationId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public Types getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelID=" + hotelID +
                ", locationId=" + locationId +
                ", hotelName='" + hotelName + '\'' +
                ", noRooms=" + noRooms +
                ", pricePerNight=" + pricePerNight +
                ", type=" + type +
                '}';
    }
}
