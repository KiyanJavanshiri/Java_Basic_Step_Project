package flightreservation.service;

import flightreservation.dao.FlightDao;
import flightreservation.models.Flight;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FlightsService {

    private final FlightDao flightDao;

    public FlightsService() {
        this.flightDao = new FlightDao();
    }

    public List<Flight> generateListOfFlight() {
        return flightDao.generateListOfFlight();
    }

    public List<Flight> getAllFlights(){
        return flightDao.getAllFlights();
    }

    public void displayFlightsForToday() {
        if (flightDao.getAllFlights().isEmpty()) {
            System.out.println("Flights for today is not available or you should generate list of flights!");
        } else {
            AtomicInteger counter = new AtomicInteger();
            System.out.printf("List of flights for %s:\n", LocalDate.now());
            flightDao.getAllFlights().stream()
                    .filter(flight -> flight.getFlightDate().equals(LocalDate.now()))
                    .sorted(Comparator.comparing(Flight::getFlightTime))
                    .forEach(flight -> System.out.printf("%d) %s%n", counter.incrementAndGet(), flight));

        }

    }

    public Flight getFlightById(String id){
        return flightDao.getFlightById(id);
    }


}
