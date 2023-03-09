package com.example.ad_proyectospring;

import com.example.ad_proyectospring.flights.Flight;
import com.example.ad_proyectospring.flights.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private FlightService flightService;

    @GetMapping({"/flights", "/"})
    public String flightList(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flights"; // Nos lleva al html flights
    }

    @GetMapping("/flights/new")
    public String createFlightForm(Model model) {
        Flight flight = new Flight();
        model.addAttribute("flight", flight);
        return "create_flight";
    }

    @PostMapping("/flights")
    public String saveFlight(@ModelAttribute("flight") Flight flight) {
        flightService.saveFlight(flight);
        return "redirect:flights";
    }

    @GetMapping("/flights/edit/{id}")
    public String createEditFlightForm(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightFromId(id));
        return "edit_flight";
    }

    @PostMapping("/flights/{id}")
    public String editFlight(@PathVariable Long id, @ModelAttribute("flight") Flight flight, Model model) {
        Flight existingFlight = flightService.getFlightFromId(id);
        existingFlight.setId(id);
        existingFlight.setPilot(flight.getPilot());
        existingFlight.setPassengers(flight.getPassengers());
        existingFlight.setEmptySeats(flight.getEmptySeats());
        existingFlight.setOrigin(flight.getOrigin());
        existingFlight.setDestination(flight.getDestination());
        existingFlight.setDate(flight.getDate());

        flightService.editFlight(existingFlight);
        return "redirect:/flights";
    }

    @GetMapping("/flights/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return "redirect:/flights";
    }

}
