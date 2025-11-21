package flightreservation.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Flight implements Serializable {
    private int id;                     // Уникальный идентификатор рейса
    private String destination;         // Место назначения
    private LocalDateTime departureTime; // Дата и время вылета
    private int availableSeats;         // Количество свободных мест

    // Конструктор
    public Flight(String destination, LocalDateTime departureTime, int availableSeats) {
        this.id = (int) (Math.random() * 10000);
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    // Удобный метод для уменьшения количества мест при бронировании
    public void reserveSeats(int seats) {
        if (seats <= availableSeats) {
            availableSeats -= seats;
        } else {
            throw new IllegalArgumentException("Недостаточно свободных мест на рейсе");
        }
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
