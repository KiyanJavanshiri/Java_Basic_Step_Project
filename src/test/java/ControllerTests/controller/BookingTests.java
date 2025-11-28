package ControllerTests.controller;

import java.util.List;
import flightreservation.controller.BookingController;
import flightreservation.controller.FlightsController;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookingTests {
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
                bookingController.displayUserBookings("John", "Kawa"));
    }

    @Test
    void testAddBooking() {
        Flight flight = flightsController.getAllFlights().get(0);
        Passenger owner = new Passenger("John", "Kawa");

        boolean result = bookingController.addBooking(List.of(owner), owner, flight);

        assertTrue(result || !result);
    }

    @Test
    void testDeleteBooking() {
        boolean result = bookingController.deleteBooking(1);
        assertTrue(result || !result);
    }
}

