package com.example.hotelmanagement.config;

import com.example.hotelmanagement.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private HotelService hotelService;

    @Override
    public void run(String... args) throws Exception {
        String filePath = "src/main/resources/hotels.json";
        hotelService.addHotelsFromJson(filePath);
    }
}