package com.example.ofertev.repository;

import com.example.ofertev.domain.Client;
import com.example.ofertev.domain.Hotel;
import com.example.ofertev.utils.Hobbies;
import com.example.ofertev.utils.Types;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelRepository {
    private String username;
    private String password;
    private String url;
    private Connection connection;

    public HotelRepository(String username, String password, String url) {
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
    public  Iterable<Hotel> findByLocation(Double id) {
        String selectSQL="SELECT * FROM hotels WHERE locationid = ?";
        List<Hotel> hotels = new ArrayList<>();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {
            preparedStatement.setDouble(1, id);
            ResultSet items = preparedStatement.executeQuery();
            while(items.next())
            {
                Double hotelID = items.getDouble("hotelid");
                Double locationId = items.getDouble("locationid");
                String hotelName = items.getString("name");
                int noRooms = items.getInt("norooms");
                double pricePerNight = items.getDouble("price");
                Types type = Types.valueOf(items.getString("type").toUpperCase());
                Hotel hotel = new Hotel(hotelID, locationId, hotelName, noRooms, pricePerNight, type);
                hotels.add(hotel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }
    public Hotel findOne(double id)
    {
        String selectSQL="SELECT * FROM hotels WHERE hotelid = ?";

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {

            preparedStatement.setDouble(1, id);
            ResultSet items = preparedStatement.executeQuery();
            if(items.next())
            {
                Double hotelID = items.getDouble("hotelid");
                Double locationId = items.getDouble("locationid");
                String hotelName = items.getString("name");
                int noRooms = items.getInt("norooms");
                double pricePerNight = items.getDouble("price");
                Types type = Types.valueOf(items.getString("type").toUpperCase());
                return new Hotel(hotelID, locationId, hotelName, noRooms, pricePerNight, type);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Iterable<Hotel> findAll() {
        String selectSQL="SELECT * FROM hotels";
        List<Hotel> hotels = new ArrayList<>();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {

            ResultSet items = preparedStatement.executeQuery();
            while(items.next())
            {
                Double hotelID = items.getDouble("hotelid");
                Double locationId = items.getDouble("locationid");
                String hotelName = items.getString("name");
                int noRooms = items.getInt("norooms");
                double pricePerNight = items.getDouble("price");
                Types type = Types.valueOf(items.getString("type").toUpperCase());
                Hotel hotel = new Hotel(hotelID, locationId, hotelName, noRooms, pricePerNight, type);
                hotels.add(hotel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotels;
    }
}
