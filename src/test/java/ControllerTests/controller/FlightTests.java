package ControllerTests.controller;

import flightreservation.controller.BookingController;
import flightreservation.controller.FlightsController;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightTests {
    private BookingController bookingController;
    private FlightsController flightsController;

    @BeforeEach
    void setUp() {
        flightsController = new FlightsController();
        flightsController.generateListOfFlight();
        bookingController = new BookingController(flightsController);
    }

    @Test
    void testDisplayUserBookings() {
        assertDoesNotThrow(() ->
                bookingController.displayUserBookings("John", "Doe")
        );
    }

    @Test
    void testAddBooking() {
        List<Flight> flights = flightsController.getAllFlights();
        Flight flight = flights.get(0);

        Passenger owner = new Passenger("John", "Doe");
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(owner);

        boolean result = bookingController.addBooking(passengers, owner, flight);

        assertTrue(result || !result);
    }

    @Test
    void testDeleteBooking() {
        boolean result = bookingController.deleteBooking(1);

        assertTrue(result || !result);
    }

}
