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

    public void displayUserBookings() {
        List<Booking> bookings = this.bookingDao.getAllBookings();

        if(bookings.isEmpty()) {
            System.out.println("Sorry, you have no any bookings yet!");
        } else {
            bookings.forEach((booking -> {
                int bookingNumber = bookings.indexOf(booking) + 1;
                System.out.println("Booking №" + bookingNumber + ": " + booking);
            }));
        }
    }

    public boolean addBooking(List<Passenger> passengers, Passenger bookingOwner, Flight flight) {
        Booking newCreatedBooking = new Booking(flight, passengers, bookingOwner);
        return this.bookingDao.addBooking(newCreatedBooking);
    }

    public boolean deleteBooking(int id) {
        Booking foundedBooking = this.getAllBookings().stream().filter((booking -> booking.getId() == id)).findFirst().orElseThrow(() -> new BookingNotFoundException());
        return this.bookingDao.deleteBooking(foundedBooking);
    }

    public List<Booking> getPassengerBookings(String firstName, String lastName) {
        return this.getAllBookings().stream().filter((booking) -> {
            boolean isAppropriate = false;
            for(Passenger passenger : booking.getPassengers()) {
                if(passenger.getFirstName().equalsIgnoreCase(firstName) && passenger.getLastName().equalsIgnoreCase(lastName)) {
                    isAppropriate = true;
                    break;
                }
            }
            return isAppropriate;
        }).collect(Collectors.toList());
    }
}
