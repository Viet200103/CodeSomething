package business.entity;

public class ReservationFlight {
    private String flightNumber;

    private String reservationID;

    private String passengerName;
    private String passengerPhone;

    private boolean isCheckIn;

    public ReservationFlight(String flightNumber, String reservationID, String passengerName, String passengerPhone, boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
        this.flightNumber = flightNumber;
        this.reservationID = reservationID;
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
    }

    public boolean isCheckIn() {
        return isCheckIn;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getReservationID() {
        return reservationID;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }
}
