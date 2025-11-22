package flightreservation.controller;

import flightreservation.models.Flight;
import flightreservation.service.FlightsService;

import java.time.LocalDate;
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

    public void searchFlights (String destination, LocalDate dateOfFlight, int tickets){
        flightsService.searchFlights(destination, dateOfFlight, tickets);
    }

    public boolean loadFlightsFromDB() {
        return flightsService.loadFlightsFromDB();
    }

    public boolean saveFlightsToDB(List<Flight> flightsList){
        return flightsService.saveFlightsToDB(flightsList);
    }

    public boolean updateFlight(Flight updatedFlight){
        return flightsService.updateFlight(updatedFlight);
    }

    public boolean updateFlight(String id, int bookedTickets) {
        return flightsService.updateFlight(id, bookedTickets);
    }

}
