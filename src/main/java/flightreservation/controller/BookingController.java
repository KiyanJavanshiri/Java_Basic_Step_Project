package flightreservation.controller;

import flightreservation.models.Flight;
import flightreservation.models.Passenger;
import flightreservation.service.BookingService;

import java.util.List;

public class BookingController {
    private BookingService bookingService;

    public BookingController(FlightsController flightsController) {
        bookingService = new BookingService(flightsController);
    }

    public void displayUserBookings(String firstName, String lastName) {
        this.bookingService.displayUserBookings(firstName, lastName);
    }

    public boolean addBooking(List<Passenger> passengers, Passenger bookingOwner, Flight flight) {
        return this.bookingService.addBooking(passengers, bookingOwner, flight);
    }

    public boolean deleteBooking(int id) {
        return this.bookingService.deleteBooking(id);
    }
}
