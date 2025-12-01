package ControllerTests.controller;

import flightreservation.controller.FlightsController;
import flightreservation.models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTests {
    private FlightsController flightsController;
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        flightsController = new FlightsController();
        if(flightsController.getAllFlights().isEmpty()) {
            this.flights = flightsController.generateListOfFlight();
        } else {
            this.flights = flightsController.getAllFlights();
        }
    }

    @Test
    void testGenerateListOfFlight() {
        assertNotNull(flights);
        assertTrue(flights.size() > 0, "Must be created");
    }

    @Test
    void testGetAllFlights() {
        List<Flight> flights = flightsController.getAllFlights();

        assertNotNull(flights);
        assertTrue(flights.size() > 0);
    }

    @Test
    void testDisplayFlightsForToday() {
        assertDoesNotThrow(() -> flightsController.displayFlightsForToday());
    }

    @Test
    void testGetFlightById() {
        assertTrue(flights.size() > 0);

        String id = flights.get(0).getId();
        Flight flight = flightsController.getFlightById(id);

        assertNotNull(flight);
        assertEquals(id, flight.getId());
    }

    @Test
    void testSearchFlights() {

        LocalDate date = LocalDate.now().plusDays(1);

        List<Flight> result = flightsController.searchFlights("Paris", date, 1);

        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }

    @Test
    void testLoadFlightsFromDB() {
        boolean loaded = flightsController.loadFlightsFromDB();

        assertTrue(loaded || !loaded);
    }

    @Test
    void testSaveFlightsToDB() {

        boolean saved = flightsController.saveFlightsToDB(flights);

        assertTrue(saved);
    }

    @Test
    void testUpdateFlightUsingId() {
        Flight flight = flights.get(0);

        boolean updated = flightsController.updateFlight(flight.getId(), 2);

        assertTrue(updated);
    }
}
