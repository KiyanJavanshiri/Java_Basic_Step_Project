package flightreservation.service;

import flightreservation.dao.FlightDao;
import flightreservation.models.Flight;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FlightsService {

    private final FlightDao flightDao;

    public FlightsService() {
        this.flightDao = new FlightDao();
    }

    public List<Flight> generateListOfFlight() {
        return flightDao.generateListOfFlight();
    }

    public List<Flight> getAllFlights() {
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

    public Flight getFlightById(String id) {
        return flightDao.getFlightById(id);
    }

    public List<Flight> searchFlights(String destination, LocalDate dateOfFlight, int tickets) {
        AtomicInteger counter = new AtomicInteger();

        System.out.printf("List of flights to %s for %s with available %d tickets:\n", destination, dateOfFlight, tickets);
        List<Flight> currentDateFlights = flightDao.getAllFlights().stream()
                .filter(flight -> flight.getDestination().toString().equalsIgnoreCase(destination) &&
                        flight.getFlightDate().equals(dateOfFlight) &&
                        flight.getAvailableSeats() >= tickets)
                .collect(Collectors.toList());
        if (!currentDateFlights.isEmpty()) {
            currentDateFlights.forEach(flight -> System.out.printf("%d) %s%n", counter.incrementAndGet(), flight));
        } else System.out.println("Flights or thickets not enough to this destination!");
        return currentDateFlights;
    }

    public boolean loadFlightsFromDB() {
        return flightDao.loadFlightsFromDB();
    }

    public boolean saveFlightsToDB(List<Flight> flightsList) {
        return flightDao.saveFlightsToDB(flightsList);
    }

    public boolean updateFlight(Flight updatedFlight) {
        List<Flight> flights = flightDao.getAllFlights();

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getId().equalsIgnoreCase(updatedFlight.getId())) {
                flights.set(i, updatedFlight);
                return true;
            }
        }
        return false;
    }

    public boolean updateFlight(String id, int bookedTickets) {
        Flight flight = flightDao.getFlightById(id);

        if (flight != null && flight.getAvailableSeats() - bookedTickets > -1) {
            flight.setAvailableSeats(flight.getAvailableSeats() - bookedTickets);
            System.out.printf("Congratulation! Tickets is booked. Information about flight %s updated at flight list!\n", id);
            return true;
        } else {
            System.out.printf("Please check information for booking tickets to flight %s!\n", id);
            return false;
        }
    }
}