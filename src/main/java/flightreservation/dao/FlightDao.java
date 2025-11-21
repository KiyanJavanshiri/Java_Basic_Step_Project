package flightreservation.dao;

import flightreservation.enums.Destination;
import flightreservation.models.Flight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class FlightDao {
    public List<Flight> flightsList = new ArrayList<>();
    private final String DB_PATH = "flightreservation/database/";

    public List<Flight> generateListOfFlight() {
        int QUANTITY_FLIGHTS_PER_DAY = 20;
        int QUANTITY_FLIGHT_SCHEDULE = 100;
        Random rnd = new Random();
        int flightIdCounter = 0;
        List<Destination> destinations = Arrays.stream(Destination.values()).collect(Collectors.toList());

        for (long j = 0; j < QUANTITY_FLIGHT_SCHEDULE; j++) {
            for (int i = 0; i < QUANTITY_FLIGHTS_PER_DAY; i++) {
                String flightId = "FL" + String.format("%04d", ++flightIdCounter);
                Destination flightDestination = destinations.get(rnd.nextInt(destinations.size()));
                int flightHour = rnd.nextInt(24);
                int flightminute = rnd.nextInt(60);
                LocalTime flightTime = LocalTime.of(flightHour, flightminute);
                int flightAvailableSeats = 2 + rnd.nextInt(7);
                Flight flight = new Flight(flightId, flightDestination, LocalDate.now().plusDays(j), flightTime, flightAvailableSeats);
                flightsList.add(flight);
            }
        }
        saveFlightsToDB(flightsList);
        return flightsList;
    }

    public List<Flight> getAllFlights() {
        return flightsList;
    }

    public Flight getFlightById(String id) {
        for (Flight flight : flightsList) {
            if (flight.getId().equals(id)) return flight;
        }
        System.out.printf("Flight with ID %s is not exist!\n", id);
        return null;
    }

    public void loadFlightsFromDB() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DB_PATH + "flights.dat"))) {
            flightsList = (List<Flight>) inputStream.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean saveFlightsToDB(List<Flight> flightsList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DB_PATH + "flights.dat"))) {
            outputStream.writeObject(flightsList);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void updateFlight(Flight flight) {

    }
}
