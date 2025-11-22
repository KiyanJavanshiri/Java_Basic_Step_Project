package flightreservation.exceptions;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {
        super("Booking was not founded");
    }
}
