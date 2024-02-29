package com.example.ofertev.repository;

import com.example.ofertev.domain.Client;
import com.example.ofertev.utils.Hobbies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private String username;
    private String password;
    private String url;
    private Connection connection;

    public ClientRepository(String username, String password, String url) {
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

    public Client findOne(long id)
    {
        String selectSQL="SELECT * FROM clients WHERE clientid = ?";

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {

            preparedStatement.setLong(1, id);
            ResultSet items = preparedStatement.executeQuery();
            if(items.next())
            {
                Long clientId = items.getLong("clientid");
                String name = items.getString("name");
                int fidelityGrade = items.getInt("grade");
                int age = items.getInt("age");
                Hobbies hobbies = Hobbies.valueOf(items.getString("hobby").toUpperCase());
                return new Client(clientId, name, fidelityGrade, age, hobbies);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Iterable<Client> findAll() {
        String selectSQL="SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try(PreparedStatement preparedStatement = this.connection.prepareStatement(selectSQL)) {

            ResultSet items = preparedStatement.executeQuery();
            while(items.next())
            {
                Long clientId = items.getLong("clientid");
                String name = items.getString("name");
                int fidelityGrade = items.getInt("grade");
                int age = items.getInt("age");
                Hobbies hobbies = Hobbies.valueOf(items.getString("hooby").toUpperCase());
                Client client = new Client(clientId, name, fidelityGrade, age, hobbies);
                clients.add(client);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }
}
