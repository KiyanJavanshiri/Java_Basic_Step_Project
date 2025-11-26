package flightreservation;

import flightreservation.controller.FlightsController;
import flightreservation.enums.Destination;
import flightreservation.models.Flight;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Flight flight = new Flight("RND123", Destination.BEIJING, LocalDate.now(), LocalTime.now(),10);
        System.out.println(flight);

        FlightsController flightsController = new FlightsController();

//        List<Flight> flights = flightsController.generateListOfFlight();
        if (flightsController.loadFlightsFromDB()){
            flightsController.displayFlightsForToday();
        }
//        List<Flight> flights = flightsController.loadFlightsFromDB();
        flightsController.displayFlightsForToday();
        flightsController.getAllFlights();

        System.out.println(flightsController.getFlightById("FL20000"));

        List<Flight> searchFlights = flightsController.searchFlights("Seoul", LocalDate.of(2025,12, 10), 3);
        System.out.println(searchFlights);
        flightsController.updateFlight("FL0040", 3);
        System.out.println(flightsController.getFlightById("FL0040"));
    }
}
