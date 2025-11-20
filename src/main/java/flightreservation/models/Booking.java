package flightreservation.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public final class Booking implements Serializable {
    private int id;
    private Flight flight;
    private List<Passenger> passengers;
    private Passenger bookingOwner;

    public Booking(Flight flight, List<Passenger> passengers, Passenger bookingOwner) {
        this.flight = flight;
        this.passengers = passengers;
        this.bookingOwner = bookingOwner;
        this.id = (int) (Math.random() * 100000);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Passenger getBookingOwner() {
        return bookingOwner;
    }

    public void setBookingOwner(Passenger bookingOwner) {
        this.bookingOwner = bookingOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(flight, booking.flight) && Objects.equals(passengers, booking.passengers) && Objects.equals(bookingOwner, booking.bookingOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flight, passengers, bookingOwner);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", flight=" + flight +
                ", passengers=" + passengers +
                ", bookingOwner=" + bookingOwner +
                '}';
    }
}
