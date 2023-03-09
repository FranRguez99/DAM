package com.example.ad_proyectospring.flights;

import java.util.List;

public interface FlightService {

    public List<Flight> getAllFlights();

    public Flight saveFlight(Flight flight);

    public Flight editFlight(Flight flight);

    public void deleteFlight(Long id);

    public Flight getFlightFromId(Long id);

}
