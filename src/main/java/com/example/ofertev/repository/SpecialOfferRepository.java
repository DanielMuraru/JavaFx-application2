package com.example.ofertev.repository;

import com.example.ofertev.domain.Reservation;
import com.example.ofertev.domain.SpecialOffer;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpecialOfferRepository {
    private String username;
    private String password;
    private String url;
    private Connection connection;

    public SpecialOfferRepository(String username, String password, String url) {
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

    public Iterable<SpecialOffer> findFromAPeriod(Double hotelid,String sDate, String eDate) {
        String selectSQL="SELECT * FROM specialoffers WHERE startdate > ? and enddate < ? and hotelid = ?";
        List<SpecialOffer> specialoffers = new ArrayList<>();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(sDate));
            preparedStatement.setDate(2, java.sql.Date.valueOf(eDate));
            preparedStatement.setDouble(3, hotelid);
            ResultSet items = preparedStatement.executeQuery();
            while(items.next())
            {
                Double specialOfferId = items.getDouble("offerid");
                double hotelId = items.getDouble("hotelid");
                Date startDate = items.getDate("startdate");
                Date endDate = items.getDate("enddate");
                int percents = items.getInt("percents");
                SpecialOffer specialOffer = new SpecialOffer(specialOfferId, hotelId, startDate, endDate, percents);
                specialoffers.add(specialOffer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialoffers;
    }

    public Iterable<SpecialOffer> findAll() {
        String selectSQL="SELECT * FROM specialoffers";
        List<SpecialOffer> specialoffers = new ArrayList<>();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {

            ResultSet items = preparedStatement.executeQuery();
            while(items.next())
            {
                Double specialOfferId = items.getDouble("offerid");
                double hotelId = items.getDouble("hotelid");
                Date startDate = items.getDate("startdate");
                Date endDate = items.getDate("enddate");
                int percents = items.getInt("percents");
                SpecialOffer specialOffer = new SpecialOffer(specialOfferId, hotelId, startDate, endDate, percents);
                specialoffers.add(specialOffer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialoffers;
    }

    public Iterable<SpecialOffer> findByFidelity(Integer fidelity) {
        String selectSQL="SELECT * FROM specialoffers WHERE percents < ?";
        List<SpecialOffer> specialoffers = new ArrayList<>();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {
            preparedStatement.setInt(1, fidelity);
            ResultSet items = preparedStatement.executeQuery();
            while(items.next())
            {
                Double specialOfferId = items.getDouble("offerid");
                double hotelId = items.getDouble("hotelid");
                Date startDate = items.getDate("startdate");
                Date endDate = items.getDate("enddate");
                int percents = items.getInt("percents");
                SpecialOffer specialOffer = new SpecialOffer(specialOfferId, hotelId, startDate, endDate, percents);
                specialoffers.add(specialOffer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return specialoffers;
    }
}
