package com.example.ofertev.repository;

import com.example.ofertev.domain.Location;
import com.example.ofertev.domain.Reservation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private String username;
    private String password;
    private String url;
    private Connection connection;

    public ReservationRepository(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
        this.establishConnection();
    }

    private void establishConnection() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addReservation(Reservation reservation) {
        String insertSQL = "INSERT INTO reservations(reservationid,clientid,hotelid,startdate,nonights) " +
                "VALUES (?,?,?,?,?)";
        String selectSQL="SELECT MAX(reservationid) as reservationid FROM reservations";
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL);
            PreparedStatement preparedStatement2 = this.connection.prepareStatement(insertSQL)) {

            ResultSet item = preparedStatement.executeQuery();
            if(item.next())
             preparedStatement2.setDouble(1, item.getDouble("reservationid")+1);
            else
                preparedStatement2.setDouble(1,1);
            preparedStatement2.setLong(2,reservation.getClientId());
            preparedStatement2.setDouble(3, reservation.getHotelId());
            preparedStatement2.setDate(4, reservation.getStartDate());
            preparedStatement2.setInt(5, reservation.getNoNights());
            preparedStatement2.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public Iterable<Reservation> findAll() {
        String selectSQL="SELECT * FROM reservations";
        List<Reservation> reservations = new ArrayList<>();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {

            ResultSet items = preparedStatement.executeQuery();
            while(items.next())
            {
                Double reservationId = items.getDouble("reservationid");
                Long clientId = items.getLong("clientid");
                double hotelId = items.getDouble("hotelid");
                LocalDateTime startDate = items.getTimestamp("startdate").toLocalDateTime();
                int noNights = items.getInt("nonights");
                Reservation reservation = new Reservation(reservationId, clientId, hotelId, startDate, noNights);
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }


}
