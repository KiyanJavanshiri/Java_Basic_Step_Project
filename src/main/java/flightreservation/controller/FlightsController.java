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

    public List<Flight> getAllFlights(){
        return flightsService.getAllFlights();
    }

    public void displayFlightsForToday() {
        flightsService.displayFlightsForToday();
    }

    public Flight getFlightById(String id){
        return flightsService.getFlightById(id);
    }

}
