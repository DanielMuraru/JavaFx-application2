package com.example.ofertev.service;

import com.example.ofertev.domain.Client;
import com.example.ofertev.repository.ClientRepository;
import com.example.ofertev.utils.Hobbies;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public Client findOne(long id)
    {
        return clientRepository.findOne(id);
    }

    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }
}
