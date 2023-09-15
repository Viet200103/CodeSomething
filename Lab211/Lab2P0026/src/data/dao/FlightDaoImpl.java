package data.dao;

import business.entity.Flight;
import data.managers.FileManager;
import data.managers.IFileManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FlightDaoImpl implements IFlightDao {

    private IFileManager fileManager;

    private HashMap<String, Flight> fMap = new HashMap<>();

    public FlightDaoImpl() {
        try {
            fileManager = new FileManager("flight.txt");
            loadFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadFromFile() throws IOException {
        List<String> rawList = fileManager.readDataFromFile();
        rawList.forEach(
                (raw) -> {
                    Flight flight = rawConvertTo(raw);
                    fMap.put(flight.getFlightNumber(), flight);
                }
        );
    }

    private Flight rawConvertTo(String raw) {
        String[] components = raw.split(",");
        String seats = components[8];
        return new Flight(
                components[0],
                components[1],
                components[2],
                components[3],
                components[4],
                components[5],
                components[6],
                components[7],
                Set.of(
                        seats.substring(1, seats.length() - 2).split(" ")
                )
        );
    }

    @Override
    public boolean addFlight(Flight flight) {

        if (fMap.containsKey(flight.getFlightNumber())) {
            throw new IllegalArgumentException("Flight number is duplicated!.");
        }

        fMap.put(flight.getFlightNumber(), flight);

        return false;
    }

    @Override
    public List<Flight> searchFlight(String deLoc, String deDate, String desLoc, String desDate) {

        String deLocFormatted = deLoc.toLowerCase();
        String desLocFormatted = desLoc.toLowerCase();

        return fMap.values().stream().filter(
                (flight) -> {

                    if (!flight.isAvailable()) return false;

                    boolean isValidDeLoc = deLocFormatted.isBlank() || flight.getDepartureLocation().toLowerCase().contains(deLocFormatted);
                    boolean isValidDeDate = true;
                    boolean isValidDesLoc = desLocFormatted.isBlank() || flight.getDestinationLocation().toLowerCase().contains(desLocFormatted);
                    boolean isValidDesDate = true;

                    return isValidDeLoc && isValidDeDate && isValidDesLoc && isValidDesDate;
                }
        ).toList();
    }

    @Override
    public List<Flight> loadAllFlight() {
        return fMap.values().stream().toList();
    }
}
