package com.example.ad_proyectospring.flights;

import jakarta.persistence.*;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   @Column(name = "pilot", nullable = false, length = 50)
    private String pilot;

    @Column(name = "passengers", nullable = false, length = 3)
    private Integer passengers;

    @Column(name = "emptySeats", nullable = false, length = 3)
    private Integer emptySeats;

    @Column(name = "origin", nullable = false, length = 50)
    private String origin;

    @Column(name = "destination", nullable = false, length = 50)
    private String destination;

    @Column(name = "date", nullable = false, length = 50)
    private LocalDateTime date;

    public Flight() {
    }

    public Flight(String pilot, Integer passengers, Integer emptySeats, String origin, String destination, LocalDateTime date) {
        this.pilot = pilot;
        this.passengers = passengers;
        this.emptySeats = emptySeats;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public Flight(long id, String pilot, Integer passengers, Integer emptySeats, String origin, String destination, LocalDateTime date) {
        this.id = id;
        this.pilot = pilot;
        this.passengers = passengers;
        this.emptySeats = emptySeats;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    public Integer getPassengers() {
        return passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public Integer getEmptySeats() {
        return emptySeats;
    }

    public void setEmptySeats(Integer emptySeats) {
        this.emptySeats = emptySeats;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
