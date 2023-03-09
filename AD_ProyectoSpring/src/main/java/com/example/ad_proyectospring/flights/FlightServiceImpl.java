package com.example.ad_proyectospring.flights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService{

    @Autowired
    private FlightRepository repository;

    @Override
    public List<Flight> getAllFlights() {
        return repository.findAll();
    }

    @Override
    public Flight saveFlight(Flight flight) {
        flight.setEmptySeats(flight.getPassengers());
        return repository.save(flight);
    }

    @Override
    public Flight editFlight(Flight flight) {
        flight.setEmptySeats(flight.getPassengers());
        return repository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Flight getFlightFromId(Long id) {
        return repository.findById(id).get();
    }

}
