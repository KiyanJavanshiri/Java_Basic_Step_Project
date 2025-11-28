package flightreservation;
import flightreservation.controller.BookingController;
import flightreservation.controller.FlightsController;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ApplicationConsole {


        private final Scanner scanner = new Scanner(System.in);

        private final FlightsController flightController = new FlightsController();
        private final BookingController bookingController = new BookingController(flightController);

        public void start() {
            boolean loud = flightController.loadFlightsFromDB();
            System.out.println(loud);
            while (true) {

                printMenu();

                int choice = nextIntInput("Choose menu item: ");
                if (choice == 6) {
                    exitApp();
                    break;
                }
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

        private int nextIntInput(String message) {
            while (true) {
                System.out.print(message);
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number!");
                    scanner.next();
                    continue;
                }
                int number = scanner.nextInt();
                scanner.nextLine();
                return number;
            }
        }

        private String nextLineInput(String message) {
            String respond;
            while (true) {
                System.out.print(message);
                respond = scanner.nextLine().trim();
                if (respond.isEmpty()) {
                    System.out.println("Enter correct string! (no empty)");
                } else {
                    return respond;
                }
            }
        }

        private void showOnlineBoard() {
            flightController.displayFlightsForToday();
        }

        private void showFlightInfo() {
            String idStr = nextLineInput("Enter flight ID: ");


            Flight flight = flightController.getFlightById(idStr);

            if (flight != null) {

                System.out.println(flight);
            } else {
                System.out.println("Flight not found!");
            }
        }

        private void searchAndBook() {
            String destination = nextLineInput("Destination: ");

            int year = nextIntInput("Year (e.g. 2025): ");
            int month = nextIntInput("Month (1-12): ");
            int day = nextIntInput("Day (1-31): ");

            LocalDate date;
            try {
                date = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("[ERROR] Invalid date provided!");
                return;
            }

            int seats;
            while (true) {
                seats = nextIntInput("Number of passengers: ");
                if(seats <= 0) {
                    System.out.println("enter positive number");
                } else {
                    break;
                }
            }

            List<Flight> found = flightController.searchFlights(destination, date, seats);

            if (found == null || found.isEmpty()) {
                System.out.println("No flights found.");
                return;
            }

            System.out.println("\nFound flights:");
            for (int i = 0; i < found.size(); i++) {
                System.out.println((i + 1) + ". " + found.get(i));
            }
            System.out.println("0. Return to menu");

            int choice = nextIntInput("Your choice: ");
            if (choice == 0) return;

            if (choice < 1 || choice > found.size()) {
                System.out.println("[ERROR] Invalid flight number!");
                return;
            }

            Flight chosenFlight = found.get(choice - 1);

            System.out.println("\n--- Booking owner information ---");
            String ownerName = nextLineInput("First name: ");
            String ownerSurname = nextLineInput("Last name: ");

            Passenger owner = new Passenger(ownerName, ownerSurname);

            List<Passenger> passengers = new ArrayList<>();
            passengers.add(owner);

            if (seats > 1) {
                System.out.println("\n--- Other passengers ---");
                for (int i = 1; i < seats; i++) {
                    System.out.println("Passenger #" + (i + 1));
                    String n = nextLineInput("  First name: ");
                    String s = nextLineInput("  Last name: ");
                    passengers.add(new Passenger(n, s));
                }
            }
            bookingController.addBooking(passengers, owner, chosenFlight);

            System.out.println("Booking operation requested (see controller output).");
        }

        private void cancelBooking() {
            int id = nextIntInput("Enter booking ID: ");

            bookingController.deleteBooking(id);
        }

        private void myBookings() {
            String surname = nextLineInput("Last name: ");
            String name = nextLineInput("First name: ");

            bookingController.displayUserBookings(name, surname);
        }

        private void exitApp() {

            flightController.saveFlightsToDB(flightController.getAllFlights());
            bookingController.saveToFile();
            System.out.println("Data saved. Exiting.");

        }
    }


