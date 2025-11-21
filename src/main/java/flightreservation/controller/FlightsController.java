package flightreservation.controller;

import flightreservation.models.Flight;
import flightreservation.service.FlightsService;

import java.util.List;

public class FlightsController {
    private final FlightsService flightsService;

    public FlightsController() {
        this.flightsService = new FlightsService();
    }

    public List<Flight> generateListOfFlight() {
        return flightsService.generateListOfFlight();
    }

    public List<Flight> getFlightsForToday() {
        return flightsService.getFlightsForToday();
    }

}
