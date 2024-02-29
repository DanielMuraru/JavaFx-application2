package com.example.ofertev.service;

import com.example.ofertev.domain.Hotel;
import com.example.ofertev.domain.Location;
import com.example.ofertev.domain.SpecialOffer;
import com.example.ofertev.dtos.SpecialOfferDTO;
import com.example.ofertev.repository.HotelRepository;
import com.example.ofertev.repository.LocationRepository;
import com.example.ofertev.repository.SpecialOfferRepository;

import java.util.ArrayList;
import java.util.List;

public class SpecialOfferService {
    private SpecialOfferRepository specialOfferRepository;
    private HotelRepository hotelRepository;
    private LocationRepository locationRepository;

    public SpecialOfferService(SpecialOfferRepository specialOfferRepository,HotelRepository hotelRepository,LocationRepository locationRepository) {
        this.specialOfferRepository = specialOfferRepository;
        this.hotelRepository = hotelRepository;
        this.locationRepository = locationRepository;
    }

    public Iterable<SpecialOfferDTO> findByPeriod(Double hotelid, String sDate,String eDate)
    {
        List<SpecialOffer> specialOfferList = (List<SpecialOffer>) specialOfferRepository.findFromAPeriod(hotelid,sDate,eDate);
        List<SpecialOfferDTO> specialOffers = new ArrayList<>();
        for (SpecialOffer specialOffer : specialOfferList) {
            Hotel hotel = hotelRepository.findOne(specialOffer.getHotelId());
            Location location = locationRepository.findOne(hotel.getLocationId());
            SpecialOfferDTO specialOfferDTO = new SpecialOfferDTO(specialOffer.getSpecialOfferId(), hotel.getHotelName(),location.getLocationName(),specialOffer.getStartDate(),specialOffer.getEndDate());
            specialOffers.add(specialOfferDTO);
        }
        return specialOffers;
    }
    public Iterable<SpecialOfferDTO> findByFidelity(Integer fidelity) {
        List<SpecialOffer> specialOfferList = (List<SpecialOffer>) specialOfferRepository.findByFidelity(fidelity);
        List<SpecialOfferDTO> specialOffers = new ArrayList<>();
        for (SpecialOffer specialOffer : specialOfferList) {
            Hotel hotel = hotelRepository.findOne(specialOffer.getHotelId());
            Location location = locationRepository.findOne(hotel.getLocationId());
            SpecialOfferDTO specialOfferDTO = new SpecialOfferDTO(specialOffer.getSpecialOfferId(), hotel.getHotelName(),location.getLocationName(),specialOffer.getStartDate(),specialOffer.getEndDate());
            specialOffers.add(specialOfferDTO);
        }
        return specialOffers;
    }
    public Iterable<SpecialOffer> findAll() {
        return specialOfferRepository.findAll();
    }
}
