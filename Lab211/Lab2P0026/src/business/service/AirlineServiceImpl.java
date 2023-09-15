package business.service;

import business.entity.Flight;
import business.entity.ReservationFlight;
import data.dao.FlightDaoImpl;
import data.dao.IFlightDao;
import data.dao.IReservationDao;
import data.dao.ReservationDaoImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AirlineServiceImpl implements IAirlineService {

    private IFlightDao flightDao = new FlightDaoImpl();
    private IReservationDao reservationDao = new ReservationDaoImpl();

    @Override
    public boolean addFlight(Flight flight) throws Exception {
        return flightDao.addFlight(flight);
    }


    @Override
    public boolean reserveFlight(ReservationFlight reservationFlight) throws Exception {


        return false;
    }

    @Override
    public List<Flight> searchFlight(String deLoc, String deDate, String desLoc, String desDate) {

        return flightDao.searchFlight(deLoc, deDate, desLoc, desDate);
    }

    @Override
    public List<Flight> loadFlightList() {
        List<Flight> flightList = flightDao.loadAllFlight();
//        flightList.sort((o1, o2) -> 1);
        return flightList;
    }
}
