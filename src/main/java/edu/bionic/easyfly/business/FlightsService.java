package edu.bionic.easyfly.business;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import edu.bionic.easyfly.persistence.Flights;
import edu.bionic.easyfly.persistence.FlightsDao;

@Named
public class FlightsService {
	
	@Inject
	private FlightsDao flightsDao;
	
	public List<Flights> getAllFlightsList(){
    	return flightsDao.getAllFlights();
    }
	
	@Transactional
    public void addFlight(Flights f){
    	flightsDao.addFlight(f);
    }
	
	@Transactional
	public void updateFlight(Flights f){
		flightsDao.updateFlight(f);
	}
	
	public boolean isRemoved(Flights f){
		return flightsDao.isRemoved(f);
	}
	
	@Transactional
	public void deleteFlight(Flights f){
		flightsDao.deleteFlight(f);
	}
	
	public List<Flights> searchFlightsForCustomer(Date startDate, Date endDate,
			int departure_city, int arrival_city, int ticket_count){
		return flightsDao.searchFlightsForCustomer(startDate, endDate, departure_city, arrival_city, ticket_count);
	}
	
	public List<Flights> getCurrentFlights(){
		return flightsDao.getCurrentFlights();
	}
	
	public List<Flights> getTop10Flights(){
		return flightsDao.getTop10Flights();
	}
}
