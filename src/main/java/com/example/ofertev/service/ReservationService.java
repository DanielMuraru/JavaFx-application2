package com.example.ofertev.service;

import com.example.ofertev.domain.Hotel;
import com.example.ofertev.domain.Reservation;
import com.example.ofertev.repository.ReservationRepository;

import java.time.LocalDateTime;

public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public void addReservation(double v, Long clientid, Double hotelid, LocalDateTime localDateTime, int noNights) {
        Reservation reservation = new Reservation(v,clientid,hotelid,localDateTime, noNights);
        reservationRepository.addReservation(reservation);
    }
}
