package com.example.ofertev.domain;

import java.sql.Date;
import java.time.LocalDateTime;

public class Reservation {
    private Double reservationId;
    private long clientId;
    private double hotelId;
    private LocalDateTime startDate;
    private int noNights;

    public Reservation(Double reservationId, long clientId, double hotelId, LocalDateTime startDate, int noNights) {
        this.reservationId = reservationId;
        this.clientId = clientId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.noNights = noNights;
    }

    public Double getReservationId() {
        return reservationId;
    }

    public long getClientId() {
        return clientId;
    }

    public double getHotelId() {
        return hotelId;
    }

    public Date getStartDate() {
        return Date.valueOf(startDate.toLocalDate());
    }

    public int getNoNights() {
        return noNights;
    }

    @Override
    public String toString() {
        return "ReservationService{" +
                "reservationId=" + reservationId +
                ", clientId=" + clientId +
                ", hotelId=" + hotelId +
                ", startDate=" + startDate +
                ", noNights=" + noNights +
                '}';
    }
}
