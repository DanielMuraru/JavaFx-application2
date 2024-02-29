package com.example.ofertev.service;

import com.example.ofertev.domain.Hotel;
import com.example.ofertev.domain.Location;
import com.example.ofertev.repository.HotelRepository;
import com.example.ofertev.repository.LocationRepository;

public class LocationService {
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Iterable<Location> findAll() {
        return locationRepository.findAll();
    }
}
