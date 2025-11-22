package flightreservation.service;

import flightreservation.dao.BookingDao;
import flightreservation.models.Booking;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;
import flightreservation.BookingNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class BookingService {
    private BookingDao bookingDao = new BookingDao();

    public List<Booking> getAllBookings() {
        return this.bookingDao.getAllBookings();
    }

    public void displayUserBookings(String firstName, String lastName) {
        List<Booking> bookings = this.getAllBookings().stream().filter((booking) -> {
            boolean isAppropriate = false;
            if(booking.getBookingOwner().getFirstName().equalsIgnoreCase(firstName.trim()) && booking.getBookingOwner().getLastName().equalsIgnoreCase(lastName.trim())) {
                return true;
            }
            for(Passenger passenger : booking.getPassengers()) {
                if(passenger.getFirstName().equalsIgnoreCase(firstName.trim()) && passenger.getLastName().equalsIgnoreCase(lastName.trim())) {
                    isAppropriate = true;
                    break;
                }
            }
            return isAppropriate;
        }).collect(Collectors.toList());

        if(bookings.isEmpty()) {
            System.out.println("Sorry, you have no any bookings yet!");
        } else {
            bookings.forEach((booking -> {
                int bookingNumber = bookings.indexOf(booking) + 1;
                System.out.println("Booking №" + bookingNumber + ": " + booking + "\n");
            }));
        }
    }

    public boolean addBooking(List<Passenger> passengers, Passenger bookingOwner, Flight flight) {
        // decrementing flight seats
        Booking newCreatedBooking = new Booking(flight, passengers, bookingOwner);
        boolean isAdded = this.bookingDao.addBooking(newCreatedBooking);
        if(isAdded) {
            this.bookingDao.saveToFile();
            System.out.println("Booking added successfully");
        } else {
            System.out.println("Something went wrong in creating booking");
        }
        return isAdded;
    }

    public boolean deleteBooking(int id) {
        try {
            Booking foundedBooking = this.getAllBookings().stream().filter((booking -> booking.getId() == id)).findFirst().orElseThrow(() -> new BookingNotFoundException());
            boolean isDeleted = this.bookingDao.deleteBooking(foundedBooking);
            if(isDeleted) {
                // incrementing flight seats
                this.bookingDao.saveToFile();
                System.out.println("Booking with id " + id + " was deleted");
            } else {
                System.out.println("Something went wrong in deleting booking");
            }
            return isDeleted;
        } catch (BookingNotFoundException ex) {
            System.out.println("Failing on founding booking: " + ex.getMessage());
            return false;
        }
    }
}
