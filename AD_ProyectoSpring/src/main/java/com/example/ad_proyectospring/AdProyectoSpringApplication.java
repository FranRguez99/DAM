package com.example.ad_proyectospring;

import com.example.ad_proyectospring.flights.Flight;
import com.example.ad_proyectospring.flights.FlightRepository;
import com.example.ad_proyectospring.users.User;
import com.example.ad_proyectospring.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class AdProyectoSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdProyectoSpringApplication.class, args);
    }

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        /*
        Flight flight1 = new Flight("Manuel Pérez", 100, 100,
                "Sevilla", "Madrid", LocalDate.of(2023, 5, 14).atStartOfDay());
        flightRepository.save(flight1);
        Flight flight2 = new Flight("Carolina Ramirez", 50, 50,
                "Málaga", "Barcelona", LocalDate.of(2023, 7, 9).atStartOfDay());
        flightRepository.save(flight2);
        Flight flight3 = new Flight("Enrique Guzmán", 80, 80,
                "Madrid", "Viena", LocalDate.of(2023, 11, 27).atStartOfDay());
        flightRepository.save(flight3);


        User user = new User("admin", "admin");
        userRepository.save(user);
        */

    }
}
