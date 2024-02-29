package com.example.ofertev.dtos;

import java.util.Date;

public class SpecialOfferDTO {
    private Double specialOfferId;
    private String hotelName;
    private String locationName;
    private Date startDate;
    private Date endDate;

    public SpecialOfferDTO(Double specialOfferId, String hotelName,String locationName, Date startDate, Date endDate) {
        this.specialOfferId = specialOfferId;
        this.hotelName = hotelName;
        this.locationName = locationName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Double getSpecialOfferId() {
        return specialOfferId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocationName() {
        return locationName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
