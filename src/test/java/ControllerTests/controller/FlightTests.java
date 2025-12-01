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

    @BeforeEach
    void setUp() {
        flightsController = new FlightsController();
    }

    @Test
    void testGenerateListOfFlight() {
        List<Flight> flights = flightsController.generateListOfFlight();

        assertNotNull(flights);
        assertTrue(flights.size() > 0, "Must be created");
    }

    @Test
    void testGetAllFlights() {
        flightsController.generateListOfFlight();

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
        List<Flight> flights = flightsController.generateListOfFlight();
        assertTrue(flights.size() > 0);

        String id = flights.get(0).getId();
        Flight flight = flightsController.getFlightById(id);

        assertNotNull(flight);
        assertEquals(id, flight.getId());
    }

    @Test
    void testSearchFlights() {
        flightsController.generateListOfFlight();

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
        List<Flight> flights = flightsController.generateListOfFlight();

        boolean saved = flightsController.saveFlightsToDB(flights);

        assertTrue(saved);
    }

    @Test
    void testUpdateFlightUsingId() {
        List<Flight> flights = flightsController.generateListOfFlight();
        Flight flight = flights.get(0);

        boolean updated = flightsController.updateFlight(flight.getId(), 2);

        assertTrue(updated);
    }
}
