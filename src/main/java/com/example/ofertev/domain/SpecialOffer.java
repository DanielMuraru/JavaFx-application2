package com.example.ofertev.domain;

import java.util.Date;

public class SpecialOffer {
    private Double specialOfferId;
    private double hotelId;
    private Date startDate;
    private Date endDate;
    private int percents;

    public SpecialOffer(Double specialOfferId, double hotelId, Date startDate, Date endDate, int percents) {
        this.specialOfferId = specialOfferId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percents = percents;
    }

    public Double getSpecialOfferId() {
        return specialOfferId;
    }

    public double getHotelId() {
        return hotelId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getPercents() {
        return percents;
    }

    @Override
    public String toString() {
        return "SpecialOffer{" +
                "specialOfferId=" + specialOfferId +
                ", hotelId=" + hotelId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", percents=" + percents +
                '}';
    }
}
