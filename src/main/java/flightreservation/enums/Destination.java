package flightreservation.enums;

public enum Destination {
    LONDON ("London"),
    PARIS ("Paris"),
    BERLIN ("Berlin"),
    MADRID ("Madrid"),
    ROME ("Rome"),
    OTTAWA ("Ottawa"),
    WASHINGTON ("Washington"),
    CANBERRA ("Canberra"),
    TOKYO ("Tokyo"),
    SEOUL ("Seoul"),
    BEIJING ("Beijing"),
    NEWDELHI ("New Delhi"),
    BRASILIA ("Brasilia"),
    BUENOSAIRES ("Buenos Aires"),
    MEXICOCITY ("Mexico City"),
    CAIRO ("Cairo"),
    NAIROBI ("Nairobi"),
    ANKARA ("Ankara"),
    ATHENS ("Athens"),
    STOCKHOLM ("Stockholm");

    private final String title;

    Destination(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}