package edu.bionic.easyfly.presentation;

import java.util.Date;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.FlightsService;
import edu.bionic.easyfly.persistence.Flights;

@Named
@Scope("session")
public class FlightsListBean {
	
	private List<Flights> flights = null;
	private List<Flights> searchFlights = null;
	private List<Flights> searchFlightsForUser = null; 
	private List<Flights> top10Flights = null;
	private List<Flights> bestOffer = null;
	@Inject
	private FlightsService flightsService;
	
	public FlightsListBean(){   }
	
	public List<Flights> getFlights() {
		return flights;
	}

	public void setFlights(List<Flights> flights) {
		this.flights = flights;
	}
	
	public List<Flights> getSearchFlights() {
		return flights;
	}

	public void setSearchFlights(List<Flights> flights) {
		this.flights = flights;
	}
	
	

	public List<Flights> getSearchFlightsForUser() {
		return searchFlightsForUser;
	}

	public void setSearchFlightsForUser(List<Flights> searchFlightsForUser) {
		this.searchFlightsForUser = searchFlightsForUser;
	}

	public void refreshList(){
		flights = flightsService.getAllFlightsList();
	}

	public List<Flights> getTop10Flights() {
		return top10Flights;
	}

	public void setTop10Flights(List<Flights> top10Flights) {
		this.top10Flights = top10Flights;
	}

	public List<Flights> getBestOffer() {
		return bestOffer;
	}

	public void setBestOffer(List<Flights> bestOffer) {
		this.bestOffer = bestOffer;
	}

}
