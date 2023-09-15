package business.service;

import business.entity.Flight;
import business.entity.ReservationFlight;

import java.util.List;

public interface IAirlineService {
    boolean addFlight(Flight flight) throws Exception;

    List<Flight> searchFlight(String deLoc, String deDate, String desLoc, String desDate);

    boolean reserveFlight(ReservationFlight reservationFlight) throws Exception;

    List<Flight> loadFlightList();
}
