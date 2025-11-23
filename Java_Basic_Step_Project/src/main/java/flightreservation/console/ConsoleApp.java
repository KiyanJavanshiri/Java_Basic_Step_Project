package flightreservation.console;

import flightreservation.controller.BookingController;
import flightreservation.controller.FlightController;
import flightreservation.domain.Flight;
import flightreservation.domain.Passenger;
import flightreservation.exception.ConsoleInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleApp {

    private final Scanner scanner = new Scanner(System.in);
    private final FlightController flightController;
    private final BookingController bookingController;

    public ConsoleApp(FlightController flightController, BookingController bookingController) {
        this.flightController = flightController;
        this.bookingController = bookingController;
    }

    public void start() {
        while (true) {
            printMenu();

            int choice = readInt("Choose menu item: ");

            switch (choice) {
                case 1:
                    showOnlineBoard();
                    break;
                case 2:
                    showFlightInfo();
                    break;
                case 3:
                    searchAndBook();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    myBookings();
                    break;
                case 6:
                    exitApp();
                    break;
                default:
                    System.out.println("[ERROR] Invalid menu option!");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Online board");
        System.out.println("2. View flight information");
        System.out.println("3. Search and book a flight");
        System.out.println("4. Cancel booking");
        System.out.println("5. My bookings");
        System.out.println("6. Exit");
        System.out.println("================================");
    }

    // ---------------- INPUT METHODS ----------------

    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] You must enter a number!");
            }
        }
    }

    private String readString(String message) {
        while (true) {
            System.out.print(message);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;

            System.out.println("[ERROR] Value cannot be empty!");
        }
    }

    private LocalDate readDate(String message) {
        while (true) {
            try {
                System.out.print(message);
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException e) {
                System.out.println("[ERROR] Date must be in format YYYY-MM-DD!");
            }
        }
    }

    // ---------------- MENU ACTIONS ----------------

    private void showOnlineBoard() {
        List<Flight> flights = flightController.getFlightsForNext24Hours();
        if (flights.isEmpty()) {
            System.out.println("No flights in the next 24 hours.");
        } else {
            flights.forEach(System.out::println);
        }
    }

    private void showFlightInfo() {
        int id = readInt("Enter flight ID: ");
        Flight flight = flightController.getFlightById(id);

        if (flight == null) {
            System.out.println("Flight not found!");
        } else {
            System.out.println(flight.toFullString());
        }
    }

    private void searchAndBook() {
        String destination = readString("Destination: ");
        LocalDate date = readDate("Date (YYYY-MM-DD): ");
        int seats = readInt("Number of passengers: ");

        List<Flight> found = flightController.searchFlights(destination, date, seats);

        if (found.isEmpty()) {
            System.out.println("No flights found.");
            return;
        }

        System.out.println("\nFound flights:");
        for (int i = 0; i < found.size(); i++) {
            System.out.println((i + 1) + ". " + found.get(i));
        }
        System.out.println("0. Return to menu");

        int choice = readInt("Your choice: ");
        if (choice == 0) return;

        if (choice < 1 || choice > found.size()) {
            System.out.println("[ERROR] Invalid flight number!");
            return;
        }

        Flight chosenFlight = found.get(choice - 1);

        // ---- BOOKING OWNER ----
        System.out.println("\n--- Booking owner information ---");
        String ownerName = readString("First name: ");
        String ownerSurname = readString("Last name: ");

        Passenger owner = new Passenger(ownerName, ownerSurname);

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(owner);

        if (seats > 1) {
            System.out.println("\n--- Other passengers ---");
        }

        for (int i = 1; i < seats; i++) {
            System.out.println("Passenger #" + (i + 1));
            String n = readString("  First name: ");
            String s = readString("  Last name: ");
            passengers.add(new Passenger(n, s));
        }

        bookingController.createBooking(owner, passengers, chosenFlight);

        System.out.println("Booking successful!");
    }

    private void cancelBooking() {
        int id = readInt("Enter booking ID: ");

        if (bookingController.cancelBooking(id)) {
            System.out.println("Booking cancelled!");
        } else {
            System.out.println("Booking not found!");
        }
    }

    private void myBookings() {
        String surname = readString("Last name: ");
        String name = readString("First name: ");

        // ВИПРАВЛЕНО: передаємо правильно
        List<String> list = bookingController.findBookingsByPassenger(surname, name);

        if (list.isEmpty()) {
            System.out.println("You have no bookings.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void exitApp() {
        flightController.saveData();
        // bookingController.saveData();  ← видалено, бо цього методу нема
        System.out.println("Data saved. Exiting.");
        System.exit(0);
    }
}
