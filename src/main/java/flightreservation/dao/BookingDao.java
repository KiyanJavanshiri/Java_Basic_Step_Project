package flightreservation.dao;

import flightreservation.models.Booking;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    private List<Booking> bookings = new ArrayList<>();

    public BookingDao() {
        init();
    }

    private void init() {
        String filePath = "src/main/java/flightreservation/db/";
        String fileName = "bookings.dat";

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath + fileName))) {
            bookings = (List<Booking>) inputStream.readObject();
            System.out.println("File read");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public List<Booking> getAllBookings() {
        return  new ArrayList<>(bookings);
    }

//    public Booking getBookingById(int id) {
//
//    }

    public boolean addBooking(Booking booking) {
        // add booking to db
        return true;
    }

    public boolean deleteBooking(int id) {
        bookings.remove(id);
        return true;
    }

    public void saveToFile() {

    }
}
