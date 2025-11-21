package flightreservation.controller;

import flightreservation.models.Booking;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;
import flightreservation.service.BookingService;

import java.util.List;

public class BookingController {
    private BookingService bookingService = new BookingService();

    public List<Booking> getAllBookings() {
        return this.bookingService.getAllBookings();
    }

    public void displayUserBookings(String firstName, String lastName) {
        this.bookingService.displayUserBookings(firstName, lastName);
    }

    public void addBooking(List<Passenger> passengers, Passenger bookingOwner, Flight flight) {
        this.bookingService.addBooking(passengers, bookingOwner, flight);
    }

    public void deleteBooking(int id) {
        this.bookingService.deleteBooking(id);
    }
}
