package flightreservation;

import flightreservation.controller.BookingController;
import flightreservation.controller.FlightsController;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
//    private static FlightsController flightsController = new FlightsController();
//    private static BookingController bookingController = new BookingController(flightsController);
//    private static Scanner scanner = new Scanner(System.in);
//
//    private static List<Passenger> passengers = new ArrayList<>(Arrays.asList(
//            new Passenger("Oleksandr", "Melnyk"),
//            new Passenger("Iryna", "Koval"),
//            new Passenger("Dmytro", "Shevchenko"),
//            new Passenger("Viktoriia", "Bondarenko"),
//            new Passenger("Serhii", "Tkachenko")
//    ));
    public static void main(String[] args) {
//        flightsController.loadFlightsFromDB();
//        System.out.println("Hello to our app");
//
//        while (true) {
//            System.out.println("1. add booking");
//            System.out.println("2. delete booking by id");
//            System.out.println("3. list of my bookings");
//            System.out.println("4. exit");
//            System.out.println("5. show flights for today");
//
//            int commandNumber = scanner.nextInt();
//            scanner.nextLine();
//
//            if(commandNumber == 4) {
//                flightsController.saveFlightsToDB(flightsController.getAllFlights());
//                break;
//            }
//
//            switch (commandNumber) {
//                case 1:
//                    System.out.println("enter flight id: ");
//                    String flightId = scanner.nextLine();
//                    Flight flight = flightsController.getFlightById(flightId);
//                    System.out.println("flightId: " + flightId);
//                    System.out.println("founded flight: " + flight);
//                    System.out.println("Owner name: ");
//                    String firstName = scanner.nextLine();
//                    System.out.println("Owner surname: ");
//                    String lastName = scanner.nextLine();
//                    Passenger owner = new Passenger(firstName, lastName);
////                    passengers.add(owner);
//                    bookingController.addBooking(passengers, owner, flight);
//                    break;
//                case 2:
//                    System.out.println("enter booking id:");
//                    int bookingId = scanner.nextInt();
//                    bookingController.deleteBooking(bookingId);
//                    break;
//                case 3:
//                    System.out.println("Your name: ");
//                    String PassengerName = scanner.nextLine();
//                    System.out.println("Your surname: ");
//                    String PassengerSurname = scanner.nextLine();
//                    bookingController.displayUserBookings(PassengerName, PassengerSurname);
//                    break;
//                case 5:
//                    flightsController.displayFlightsForToday();
//            }
//        }

//        Flight flight = new Flight("RND123", Destination.BEIJING, LocalDate.now(), LocalTime.now(),10);
//        System.out.println(flight);

//        FlightsController flightsController = new FlightsController();

//        List<Flight> flights = flightsController.generateListOfFlight();
//        if (flightsController.loadFlightsFromDB()){
//            flightsController.displayFlightsForToday();
//        }
//        List<Flight> flights = flightsController.loadFlightsFromDB();
//        List<Flight> flightsToday = flightsController.getFlightsForToday();
//        flightsController.getAllFlights();
//
//        System.out.println(flightsController.getFlightById("FL20000"));
//        flightsController.searchFlights("Seoul", LocalDate.of(2025,12, 12), 3);
//        flightsController.updateFlight("FL0040", 3);
//        System.out.println(flightsController.getFlightById("FL0040"));
//        bookingController.saveToFile();
    }
}
