package ServiceTests;

import flightreservation.controller.FlightsController;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;
import flightreservation.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import flightreservation.enums.Destination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTests {

    private BookingService bookingService;
    private FlightsController flightsController;
    private List<Flight> flight;

    @BeforeEach
    void setUp() {
        flightsController = new FlightsController();
        bookingService = new BookingService(flightsController);
        if(flightsController.getAllFlights().isEmpty()) {
            this.flight = flightsController.generateListOfFlight();
        }
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
        Flight flight = new Flight(
                "FL123",
                Destination.LONDON,
                LocalDate.of(2025, 1, 1),
                LocalTime.of(12, 30),
                150
        );
        Passenger owner = new Passenger("John", "Kawa");
        List<Passenger> passengers = List.of(owner);

        assertDoesNotThrow(() ->
                bookingService.addBooking(passengers, owner, flight)
        );
    }

    @Test
    void testDeleteBooking_NotExisting() {
        assertDoesNotThrow(() ->
                bookingService.deleteBooking(999)
        );
    }
}
