package flightreservation.models;

import flightreservation.enums.Destination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private String id;
    private String departure;
    private Destination destination;
    private LocalDate flightDate;
    private LocalTime flightTime;
    private int availableSeats;

    public Flight(String id, Destination destination, LocalDate flightDate, LocalTime flightTime, int availableSeats) {
        this.id = id;
        this.departure = "Kyiv";
        this.destination = destination;
        this.flightDate = flightDate;
        this.flightTime = flightTime;
        this.availableSeats = availableSeats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination.getTitle();
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public LocalTime getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(LocalTime flightTime) {
        this.flightTime = flightTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Flight: id - " + id +
                ", from " + departure +
                " to " + destination +
                "\n        Date: " + flightDate +
                "\n        Time: " + flightTime.format(DateTimeFormatter.ofPattern("HH:mm")) +
                "\n        Available Seats: " + availableSeats;
    }
}
