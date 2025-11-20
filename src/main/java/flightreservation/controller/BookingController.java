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

    public boolean addBooking(List<Passenger> passengers, Passenger bookingOwner, Flight flight) {
        return this.bookingService.addBooking(passengers, bookingOwner, flight);
    }

    public boolean deleteBooking(int id) {
        return this.bookingService.deleteBooking(id);
    }

    public List<Booking> getPassengerBookings(String firstName, String lastName) {
        return this.bookingService.getPassengerBookings(firstName, lastName);
    }
}
