package com.example.hotelmanagement.service;

import com.example.hotelmanagement.exceptions.BookingConflictException;
import com.example.hotelmanagement.model.Booking;
import com.example.hotelmanagement.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public void addBooking(Booking booking) throws BookingConflictException {
        List<Booking> overlappingBookings = bookingRepository.findBookingsByRoomAndDateRange(
                booking.getRoom().getId(), booking.getStartDate(), booking.getEndDate());

        if (!overlappingBookings.isEmpty()) {
            throw new BookingConflictException("Room is already booked for the given dates");
        }

        bookingRepository.save(booking);
    }


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    public Booking updateBooking(Long id, Booking newBooking) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setRoom(newBooking.getRoom());
            booking.setUser(newBooking.getUser());
            booking.setStartDate(newBooking.getStartDate());
            booking.setEndDate(newBooking.getEndDate());
            booking.setTotalCost(newBooking.getTotalCost());
            return bookingRepository.save(booking);
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }

    public void deleteBooking(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            bookingRepository.delete(optionalBooking.get());
        } else {
            throw new RuntimeException("Booking not found with id: " + id);
        }
    }
}