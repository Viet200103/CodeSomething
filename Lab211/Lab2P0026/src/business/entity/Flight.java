package business.entity;

import java.util.Set;

public class Flight {
    private String flightNumber;

    private String airline;

    private String departureLocation;
    private String destinationLocation;
    private String departureDate;

    private String departureTime;
    private String arrivalDate;

    private String arrivalTime;

    private Set<String> availableSeats;

    public Flight(
            String flightNumber,
            String airline,
            String departureLocation,
            String destinationLocation,
            String departureDate,
            String departureTime,
            String arrivalDate,
            String arrivalTime,
            Set<String> availableSeats) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getAirline() {
        return airline;
    }

    public Set<String> getAvailableSeats() {
        return availableSeats;
    }

    public boolean isAvailable() {
        return !availableSeats.isEmpty();
    }

    @Override
    public String toString() {

        return  departureDate + " to " + arrivalDate +
                "\n" +
                airline.toUpperCase() +
                "\n" +
                "FROM TRIP " + departureLocation.toUpperCase() + " TO" + destinationLocation.toUpperCase() +
                "\n" +
                "FLIGHT NUMBER: " + flightNumber +
                "\n" +
                "DEPARTING AT: " + departureTime +
                "\n" +
                "ARRIVING AT: " + arrivalTime +
                "\n" +
                "AVAILABLE SEATS: " + availableSeats;
    }
}
