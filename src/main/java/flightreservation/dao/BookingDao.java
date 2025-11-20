package flightreservation.dao;

import flightreservation.models.Booking;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    private List<Booking> bookings = new ArrayList<>();

    public BookingDao() {
        init();
    }

    public List<Booking> getAllBookings() {
        return  new ArrayList<>(bookings);
    }

    public boolean addBooking(Booking booking) {
        this.bookings.add(booking);
        this.saveToFile();
        return true;
    }

    public boolean deleteBooking(Booking booking) {
        bookings.remove(booking);
        this.saveToFile();
        return true;
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

    public void saveToFile() {
        String filePath = "src/main/java/flightreservation/db/";
        String fileName = "bookings.dat";
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath + fileName))) {
            outputStream.writeObject(bookings);
            System.out.println("File read");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
