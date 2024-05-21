package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.Room;
import com.example.hotelmanagement.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }
}
