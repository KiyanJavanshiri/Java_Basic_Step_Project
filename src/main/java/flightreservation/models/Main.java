package flightreservation.models;

import flightreservation.controller.BookingController;
import flightreservation.controller.FlightsController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static FlightsController flightsController = new FlightsController();
    private static BookingController bookingController = new BookingController(flightsController);
    private static Scanner scanner = new Scanner(System.in);

    private static List<Passenger> passengers = new ArrayList<>(Arrays.asList(
            new Passenger("Oleksandr", "Melnyk"),
            new Passenger("Iryna", "Koval"),
            new Passenger("Dmytro", "Shevchenko"),
            new Passenger("Viktoriia", "Bondarenko"),
            new Passenger("Serhii", "Tkachenko")
    ));

    public static void main(String[] args) {
        flightsController.loadFlightsFromDB();
        System.out.println("Hello to our app");

        while (true) {
            System.out.println("1. add booking");
            System.out.println("2. delete booking by id");
            System.out.println("3. list of my bookings");
            System.out.println("4. exit");
            System.out.println("5. show flights for today");

            int commandNumber = scanner.nextInt();
            scanner.nextLine();

            if(commandNumber == 4) {
                flightsController.saveFlightsToDB(flightsController.getAllFlights());
                break;
            }

            switch (commandNumber) {
                case 1:
                    System.out.println("enter flight id: ");
                    String flightId = scanner.nextLine();
                    Flight flight = flightsController.getFlightById(flightId);
                    System.out.println("flightId: " + flightId);
                    System.out.println("founded flight: " + flight);
                    System.out.println("Owner name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Owner surname: ");
                    String lastName = scanner.nextLine();
                    Passenger owner = new Passenger(firstName, lastName);
//                    passengers.add(owner);
                    bookingController.addBooking(passengers, owner, flight);
                    break;
                case 2:
                    System.out.println("enter booking id:");
                    int bookingId = scanner.nextInt();
                    bookingController.deleteBooking(bookingId);
                    break;
                case 3:
                    System.out.println("Your name: ");
                    String PassengerName = scanner.nextLine();
                    System.out.println("Your surname: ");
                    String PassengerSurname = scanner.nextLine();
                    bookingController.displayUserBookings(PassengerName, PassengerSurname);
                    break;
                case 5:
                    flightsController.displayFlightsForToday();
            }
        }
    }
}
