package flightreservation;

import flightreservation.controller.FlightsController;
import flightreservation.enums.Destination;
import flightreservation.models.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        Flight flight = new Flight("RND123", Destination.BEIJING, LocalDate.now(), LocalTime.now(),10);
//        System.out.println(flight);

        FlightsController flightsController = new FlightsController();

        List<Flight> flights = flightsController.generateListOfFlight();
//        List<Flight> flightsToday = flightsController.getFlightsForToday();
        flightsController.displayFlightsForToday();

        System.out.println(flightsController.getFlightById("FL1200"));
    }
}
