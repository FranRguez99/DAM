package com.example.ad_proyectospring.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    BookingRepository repository;

    @Override
    public Booking saveBooking(Booking booking) {
        return repository.save(booking);
    }
}
