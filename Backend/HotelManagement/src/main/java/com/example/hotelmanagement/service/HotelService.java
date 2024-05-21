package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.Hotel;
import com.example.hotelmanagement.model.Room;
import com.example.hotelmanagement.repository.HotelRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Transactional
    public Hotel addHotel(Hotel hotel) {
        for (Room room : hotel.getRooms()) {
            room.setHotel(hotel);
        }
        return hotelRepository.save(hotel);
    }

    @Transactional
    public void addHotelsFromJson(String filePath) {
        var objectMapper = new ObjectMapper();
        try {
            List<Hotel> hotels = objectMapper.readValue(new File(filePath), new TypeReference<List<Hotel>>() {});
            for (Hotel hotel : hotels) {
                addHotel(hotel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }
}