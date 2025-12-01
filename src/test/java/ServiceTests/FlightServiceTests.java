package ServiceTests;

import flightreservation.models.Flight;
import flightreservation.service.FlightsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

public class FlightServiceTests {
    private FlightsService flightsService;
    private List<Flight> flight;

    @BeforeEach
    void setUp() {
        flightsService = new FlightsService();
        if(flightsService.getAllFlights().isEmpty()) {
            this.flight = flightsService.generateListOfFlight();
        }
    }

    @Test
    void testGetAllFlights() {
        List<Flight> flights = flightsService.getAllFlights();
        assertNotNull(flights);
        assertFalse(flights.isEmpty());
    }

    @Test
    void testGetFlightById() {
        Flight f = flightsService.getAllFlights().get(0);
        Flight found = flightsService.getFlightById(f.getId());

        assertNotNull(found);
        assertEquals(f.getId(), found.getId());
    }

    @Test
    void testUpdateFlightObject() {
        Flight f = flightsService.getAllFlights().get(0);

        int updatedSeats = f.getAvailableSeats() + 10;
        f.setAvailableSeats(updatedSeats);

        assertTrue(flightsService.updateFlight(f));
        assertEquals(updatedSeats, flightsService.getAllFlights().get(0).getAvailableSeats());
    }

    @Test
    void testUpdateFlightSeats() {
        Flight f = flightsService.getAllFlights().get(0);
        int before = f.getAvailableSeats();

        boolean result = flightsService.updateFlight(f.getId(), 1);

        assertTrue(result);
        assertEquals(before - 1, f.getAvailableSeats());
    }

    @Test
    void testUpdateFlightSeats_NotEnough() {
        Flight f = flightsService.getAllFlights().get(0);

        int tooMany = f.getAvailableSeats() + 100;
        boolean result = flightsService.updateFlight(f.getId(), tooMany);

        assertFalse(result);
    }

    @Test
    void testSaveFlightsToDB() {
        List<Flight> flights = flightsService.getAllFlights();
        assertDoesNotThrow(() -> flightsService.saveFlightsToDB(flights));
    }
}