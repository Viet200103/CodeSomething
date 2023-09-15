package application;

import application.ui.FlightManagement;
import application.ui.Menu;
import application.utilities.DataIOHelper;

public class Main {
    public static void main(String[] args) {

        DataIOHelper inputHelper = DataIOHelper.getInstance();
        FlightManagement fManagement = new FlightManagement();

        try {
            int choice;

            do {
                System.out.println("******Store Program******");
                Menu.print(
                        "1. Create flight" + "|" +
                                "2. Reservation & booking" + "|" +
                                "3. Check-in & assignment" + "|" +
                                "4. Crew access" + "|" +
                                "5. Administrator access" + "|" +
                                "6. Print flight list" + "|" +
                                "7. Exit"
                );
                choice = inputHelper.getIntegerNumber();
                System.out.println("--------------------------------");

                switch (choice) {
                    case 1 -> fManagement.processSchedule();
                    case 2 -> fManagement.processBooking();
                    case 3 -> {}
                    case 4 -> {}
                    case 5 -> {}
                    case 6 -> fManagement.printFlightList();
                    case 7 -> {
                        System.out.println("***Store Program is stopped***");
                        System.exit(0);
                    }
                    default -> Menu.printRequireNotFound();
                }

            } while (true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}