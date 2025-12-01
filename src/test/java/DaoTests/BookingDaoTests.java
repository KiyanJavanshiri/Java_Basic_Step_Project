package DaoTests;

import flightreservation.dao.BookingDao;
import flightreservation.enums.Destination;
import flightreservation.models.Booking;
import flightreservation.models.Flight;
import flightreservation.models.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.AssertionsKt.assertNotNull;

public class BookingDaoTests {
    private BookingDao bookingDao;

    @BeforeEach
    void setUp() {
        File file = new File("src/main/java/flightreservation/database/bookings.dat");
        if (file.exists()) file.delete();

        bookingDao = new BookingDao();
    }

    private Flight createTestFlight() {
        return new Flight(
                "FL001",
                Destination.PARIS,
                LocalDate.now(),
                LocalTime.of(12, 30),
                50
        );
    }

    private Passenger createPassenger(String name) {
        return new Passenger(name, "LastName");
    }

    private Booking createBooking() {
        Flight flight = createTestFlight();
        Passenger p1 = createPassenger("John");
        Passenger owner = createPassenger("Kawa");

        return new Booking(
                flight,
                List.of(p1),
                owner
        );
    }

    @Test
    void testInitEmptyFile() {
        List<Booking> list = bookingDao.getAllBookings();

        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void testAddBooking() {
        Booking booking = createBooking();

        boolean added = bookingDao.addBooking(booking);

        assertTrue(added);
        assertEquals(1, bookingDao.getAllBookings().size());
    }

    @Test
    void testDeleteBooking() {
        Booking booking = createBooking();
        bookingDao.addBooking(booking);

        int sizeBefore = bookingDao.getAllBookings().size();

        boolean removed = bookingDao.deleteBooking(booking);

        assertTrue(removed);

        int sizeAfter = bookingDao.getAllBookings().size();
        assertTrue(sizeAfter < sizeBefore);
    }

    @Test
    void testSaveAndLoadBookingFile() {
        Booking booking = createBooking();
        bookingDao.addBooking(booking);

        int sizeBefore = bookingDao.getAllBookings().size();

        bookingDao.saveToFile();

        BookingDao loadedDao = new BookingDao();
        List<Booking> loaded = loadedDao.getAllBookings();

        int sizeAfter = loaded.size();

        assertEquals(sizeBefore, sizeAfter);

        Booking loadedBooking = loaded.get(loaded.size() - 1);

        assertEquals(booking.getFlight().getId(), loadedBooking.getFlight().getId());
        assertEquals(booking.getPassengers().size(), loadedBooking.getPassengers().size());
    }
}
