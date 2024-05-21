package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserId(Long userId);
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND NOT (b.endDate <= :startDate OR b.startDate >= :endDate)")
    List<Booking> findBookingsByRoomAndDateRange(@Param("roomId") Long roomId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}