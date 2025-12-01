package flightreservation.dao;

import flightreservation.models.Booking;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class BookingDao {
    private List<Booking> bookings;

    public BookingDao() {
        init();
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public boolean addBooking(Booking booking) {
        return this.bookings.add(booking);
    }

    public boolean deleteBooking(Booking booking) {
       return bookings.remove(booking);
    }

    private void init() {
        String filePath = "src/main/java/flightreservation/database/";
        String fileName = "bookings.dat";
        File file = new File(filePath + fileName);

        if(!file.exists()) {
            this.bookings = new ArrayList<>();
            return;
        }

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath + fileName))) {
            bookings = (List<Booking>) inputStream.readObject();
        } catch(EOFException ex) {
            this.bookings = new ArrayList<>();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Failed to reading bookings: " + ex.getMessage());
        }
    }

    public void saveToFile() {
        String filePath = "src/main/java/flightreservation/database/";
        String fileName = "bookings.dat";
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath + fileName))) {
            outputStream.writeObject(bookings);
            System.out.println("Bookings saved to file");
        } catch (IOException ex) {
            System.out.println("Failed to save bookings: " + ex.getMessage());;
        }
    }
}
