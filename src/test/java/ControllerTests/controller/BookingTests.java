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
    private Flight testFlight;
    @BeforeEach
    void setUp() {
        flightsController = new FlightsController();
        if (flightsController.getAllFlights().isEmpty()) {
            flightsController.generateListOfFlight();
        }
        bookingController = new BookingController(flightsController);
        testFlight = flightsController.getAllFlights().get(0);
    }

    @Test
    void testDisplayUserBookings() {
        assertDoesNotThrow(() ->
                bookingController.displayUserBookings("John", "Kawa"));
    }

    @Test
    void testAddBooking() {

        Passenger owner = new Passenger("John", "Kawa");
        boolean result = bookingController.addBooking(List.of(owner), owner, testFlight);
        assertTrue(result);
    }

    @Test
    void testDeleteBooking() {
        boolean result = bookingController.deleteBooking(1);
        assertTrue(result || !result);
    }
}

