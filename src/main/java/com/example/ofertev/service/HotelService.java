package com.example.ofertev.service;

import com.example.ofertev.domain.Client;
import com.example.ofertev.domain.Hotel;
import com.example.ofertev.repository.ClientRepository;
import com.example.ofertev.repository.HotelRepository;

import java.util.List;

public class HotelService {
    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    public Hotel findOne(Integer id){
        return hotelRepository.findOne(id);
    }
    public List<Hotel> findByLocation(Double location)
    {
        return (List<Hotel>) hotelRepository.findByLocation(location);
    }

    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }
}
