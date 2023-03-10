package com.example.ad_proyectospring;

import com.example.ad_proyectospring.booking.Booking;
import com.example.ad_proyectospring.booking.BookingRepository;
import com.example.ad_proyectospring.booking.BookingService;
import com.example.ad_proyectospring.flights.Flight;
import com.example.ad_proyectospring.flights.FlightRepository;
import com.example.ad_proyectospring.flights.FlightService;
import com.example.ad_proyectospring.users.User;
import com.example.ad_proyectospring.users.UserRepository;
import com.example.ad_proyectospring.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    User loggedUser;

    @GetMapping("/")
    public String login() {
        return "userLogin";
    }

    @PostMapping({"/userLogin"})
    public String authenticateUser(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = userService.authenticateUser(username, password);
        if (isAuthenticated) {
            List<User> users = repository.findAll();
            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    loggedUser = user;
                }
            }
            if (username.equals("admin")) {
                return "redirect:/flights";
            } else {
                return "redirect:/userFlights";
            }
        } else {
            return "login?error";
        }
    }

    @GetMapping("/userFlights")
    public String userFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "userFlights";
    }

    @PostMapping("/flights/reserve/{id}")
    public String bookFlight(@PathVariable("id") Long id, Model model) {
        // Retrieve the flight that the user is booking
        Flight flight = flightRepository.getOne(id);

        // Create a new booking object that contains the user and flight information
        Booking booking = new Booking();
        booking.setUser(loggedUser);
        booking.setFlight(flight);

        // Update the number of empty seats in the flight
        flight.setEmptySeats(flight.getEmptySeats() - 1);

        // Save the booking and flight objects to the database
        bookingRepository.save(booking);
        flightRepository.save(flight);

        // Pass the flight object to the view
        model.addAttribute("flight", flight);

        // Return the view for the individual flight
        return "seeFlight";
    }



    @PostMapping("/flights/confirm")
    public String confirmBooking(@RequestParam Long flightId) {
        // Retrieve the flight and user objects
        Flight flight = flightRepository.getOne(flightId);
        User user = loggedUser;

        // Create a new booking object that contains the user and flight information
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);

        // Save the booking to the database
        bookingRepository.save(booking);

        // Redirect the user to the list of flights
        return "redirect:/userFlights";
    }




    @GetMapping("/register")
    public String registerUser(Model model) {
        // Create a new user object to store the form data
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user) {
        // Call a service method to save the user to the database
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/flights")
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
