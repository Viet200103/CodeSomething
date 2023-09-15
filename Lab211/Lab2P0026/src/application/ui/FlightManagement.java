package application.ui;

import application.utilities.DataIOHelper;
import application.utilities.DataValidation;
import business.ReservationCodeGenerator;
import business.entity.Flight;
import business.entity.ReservationFlight;
import business.service.AirlineServiceImpl;
import business.service.IAirlineService;

import java.util.Set;

public class FlightManagement {

    private IAirlineService service = new AirlineServiceImpl();

    private DataIOHelper ioHelper = DataIOHelper.getInstance();

    public void processSchedule() {
        boolean isContinue;
        do {
            try {
                String fNumber = getFlightNumber();

                String airline = ioHelper.getStringNotEmpty("Enter airline name: ", "Airline name cannot be empty");
                String deCity = ioHelper.getStringNotEmpty("Enter departure location: ", "Departure City cannot be empty.");
                String desCity = ioHelper.getStringNotEmpty("Enter destination location: ", "Destination city cannot be empty");
                String deDate = ioHelper.getDateTime("Enter departure date: ");
                String deTime = ioHelper.getTime("Enter departure time: ");
                String arrivalDate = ioHelper.getDateTime("Enter arrival date: ");
                String arrivalTime = ioHelper.getDateTime("Enter arrival time: ");

                String availableSeat = ioHelper.getStringWithMessage("Enter available seats: ");

                Flight flight = new Flight(
                        fNumber, airline, deCity, desCity, deDate, deTime, arrivalDate, arrivalTime,
                        Set.of(availableSeat.split(" "))
                );

                service.addFlight(flight);


            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
            }

            DataIOHelper.printlnMessage("--------------------------------");
            DataIOHelper.printMessage("Do you want to continue(Y/N)? ");
            isContinue = ioHelper.getString().matches("[\\s{y, Y}]");
            DataIOHelper.printlnMessage("--------------------------------");
        } while (isContinue);
    }

    public void processBooking() {
        String deLoc = ioHelper.getStringWithMessage("Enter departure location: ");
        String deDate = ioHelper.getStringWithMessage("Enter departure date: ");
        String desLoc = ioHelper.getStringWithMessage("Enter arrival location: ");
        String desDate = ioHelper.getStringWithMessage("Enter arrival date: ");

        DataIOHelper.printlnMessage("----------------Flight List----------------");

        service.searchFlight(deLoc, deDate, desLoc, desDate).forEach(
                System.out::println
        );

        DataIOHelper.printlnMessage("-------------------------------------------");

        String fNumber = ioHelper.getStringWithMessage( "Enter flight number: ");
        if (fNumber.isBlank()) {
            DataIOHelper.printlnNotification("Flight reservation is canceled");
            return;
        }

        String psName = ioHelper.getStringNotEmpty("Enter your name: ", "Your name cannot be empty");
        String psPhone = ioHelper.getStringNotEmpty("Enter your phone: ", "Your phone cannot be empty");

        boolean isBooked = ioHelper.getStringWithMessage("Do you want to book a flight? (Y/N)").matches("[\\s{y,Y}]");
        if (isBooked) {

            ReservationFlight reservationFlight = new ReservationFlight(
                    getFlightNumber(), ReservationCodeGenerator.generateReservationCode(), psName, psPhone, false
            );

            try {
                service.reserveFlight(reservationFlight);
            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
                DataIOHelper.printlnNotification("Reserve flight is failed!");
            }

        } else {
            DataIOHelper.printlnNotification("Flight reservation is canceled");
        }

    }


    private String getFlightNumber() {
        String fNumber;
        do {
            try {
                fNumber = ioHelper.getStringWithMessage("Enter flight number: ");
                ;
                DataValidation.validateFlightNumber(fNumber);
                break;
            } catch (Exception e) {
                DataIOHelper.printlnNotification(e.getMessage());
                DataIOHelper.displayTryAgainMessage();
            }
        } while (true);

        return fNumber;
    }


    public void printFlightList() {
        for (Flight f : service.loadFlightList()) {
            DataIOHelper.printMessage(f.toString());
            DataIOHelper.printlnMessage("\n-------------------------------------------");
        }
    }
}
