package ServiceTests;

import flightreservation.controller.FlightsController;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;
import flightreservation.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTests {

    private BookingService bookingService;
    private FlightsController flightsController;

    @BeforeEach
    void setUp() {
        flightsController = new FlightsController();
        flightsController.generateListOfFlight();
        bookingService = new BookingService(flightsController);
    }

    @Test
    void testGetAllBookings() {
        assertNotNull(bookingService.getAllBookings());
    }

    @Test
    void testDisplayUserBookings() {
        assertDoesNotThrow(() ->
                bookingService.displayUserBookings("John", "Kawa")
        );
    }

    @Test
    void testAddBooking() {
        Flight flight = flightsController.getAllFlights().get(0);
        Passenger owner = new Passenger("John", "Kawa");

        assertDoesNotThrow(() ->
                bookingService.addBooking(List.of(owner), owner, flight)
        );
    }

    @Test
    void testDeleteBooking_NotExisting() {
        assertDoesNotThrow(() ->
                bookingService.deleteBooking(999)
        );
    }
}
