package com.example.ofertev.repository;

import com.example.ofertev.domain.Hotel;
import com.example.ofertev.domain.Location;
import com.example.ofertev.utils.Types;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {
    private String username;
    private String password;
    private String url;
    private Connection connection;

    public LocationRepository(String username, String password, String url) {
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
    public Location findOne(double id)
    {
        String selectSQL="SELECT * FROM locations WHERE locationid = ?";

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {

            preparedStatement.setDouble(1, id);
            ResultSet items = preparedStatement.executeQuery();
            if(items.next())
            {
                Double locationId = items.getDouble("locationid");
                String locationName = items.getString("locationname");
                return new Location(locationId, locationName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Iterable<Location> findAll() {
        String selectSQL="SELECT * FROM locations";
        List<Location> locations = new ArrayList<>();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {

            ResultSet items = preparedStatement.executeQuery();
            while(items.next())
            {
                Double locationId = items.getDouble("locationid");
                String locationName = items.getString("locationname");
                Location location = new Location(locationId, locationName);
                locations.add(location);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }
}
