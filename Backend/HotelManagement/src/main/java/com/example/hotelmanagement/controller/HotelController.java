package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.model.Hotel;
import com.example.hotelmanagement.model.Room;
import com.example.hotelmanagement.service.GeolocationService;
import com.example.hotelmanagement.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private GeolocationService geolocationService;

    @PostMapping
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    @GetMapping
    public List<Hotel> getHotelsWithinRadius(@RequestParam("latitude") double latitude,
                                             @RequestParam("longitude") double longitude,
                                             @RequestParam("radius") double radius) {
        return geolocationService.getHotelsWithinRadius(latitude, longitude, radius);
    }

    @GetMapping("/{id}")
    public Optional<Hotel> getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

}