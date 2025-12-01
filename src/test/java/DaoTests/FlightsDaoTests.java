package DaoTests;
import flightreservation.models.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import flightreservation.dao.FlightDao;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightsDaoTests {
    private FlightDao dao;

    @BeforeEach
    void setUp() {
        dao = new FlightDao();
        dao.loadFlightsFromDB();
    }

    @Test
    void testGetAllFlights() {

        List<Flight> allFlights = dao.getAllFlights();

        assertInstanceOf(List.class, allFlights);
        assertEquals(2000, allFlights.size());
    }

    @Test
    void testGetFlightById_found() {

        Flight found = dao.getFlightById("FL0001");

        assertNotNull(found, "Flight FL0001 must exist");
        assertEquals("FL0001", found.getId());
    }

    @Test
    void testGetFlightById_notFound() {

        Flight notFound = dao.getFlightById("UNKNOWN");

        assertNull(notFound, "Unknown id must return null");
    }

    @Test
    void testSaveFlightsToDB() {

        boolean result = dao.saveFlightsToDB(dao.getAllFlights());

        assertTrue(result, "Saving to DB must return true");

        File file = new File("src/main/java/flightreservation/database/flights.dat");
        assertTrue(file.exists(), "flights.dat file must exist after saving");
    }

    @Test
    void testLoadFlightsFromDB() {
        dao.saveFlightsToDB(dao.getAllFlights());

        FlightDao newDao = new FlightDao();

        boolean loaded = newDao.loadFlightsFromDB();

        assertTrue(loaded, "Loading must return true");
        assertEquals(2000, newDao.getAllFlights().size(), "Loaded flights must be 2000");
    }
}
