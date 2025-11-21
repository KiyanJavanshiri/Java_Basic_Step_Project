package flightreservation.service;

import flightreservation.dao.FlightDao;
import flightreservation.models.Flight;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FlightsService {

    private final FlightDao flightDao;

    public FlightsService() {
        this.flightDao = new FlightDao();
    }

    public List<Flight> generateListOfFlight() {
        return flightDao.generateListOfFlight();
    }

    public List<Flight> getFlightsForToday() {
        return flightDao.getAllFlights().stream()
                .filter(flight -> flight.getFlightDate().equals(LocalDate.now()))
                .collect(Collectors.toList());
    }


}
