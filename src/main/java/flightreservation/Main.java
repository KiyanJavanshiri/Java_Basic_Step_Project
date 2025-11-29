package flightreservation;

import flightreservation.controller.FlightsController;
import flightreservation.enums.Destination;
import flightreservation.models.Flight;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationConsole applicationConsole = new ApplicationConsole();
        applicationConsole.start();
    }
}
