package flightreservation.models;

import java.io.Serializable;

public class Passenger implements Serializable {
    private String firstName;
    private String lastName;
    private int id;

    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = (int) (Math.random() * 100000);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FirstName: " + firstName  +
                ", LastName: " + lastName +
                ", ID: " + id
                ;
    }
}
