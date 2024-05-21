package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.Hotel;
import com.example.hotelmanagement.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import java.util.List;
@Service
public class GeolocationService {
    @Autowired
    private HotelRepository hotelRepository;


    public List<Hotel> getHotelsWithinRadius(double userLat, double userLon, double radius) {
        List<Hotel> allHotels = hotelRepository.findAll();
        List<Hotel> hotelsWithinRadius = new ArrayList<>();

        for (Hotel hotel : allHotels) {
            double distance = calculateHaversineDistance(userLat, userLon, hotel.getLatitude(), hotel.getLongitude());
            if (distance <= radius) {
                hotelsWithinRadius.add(hotel);
            }
        }
        return hotelsWithinRadius;
    }

    private double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371000;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }



}