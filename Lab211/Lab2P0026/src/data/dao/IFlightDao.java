package data.dao;

import business.entity.Flight;

import java.util.List;

public interface IFlightDao {
    boolean addFlight(Flight flight) throws Exception;

    List<Flight> searchFlight(String deLoc, String deDate, String desLoc, String desDate);

    List<Flight> loadAllFlight();
}
