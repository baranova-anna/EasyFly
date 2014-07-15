package edu.bionic.easyfly.presentation;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.primefaces.event.CellEditEvent;
import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.FlightsService;
import edu.bionic.easyfly.persistence.Flights;

@Named
@Scope("session")
public class FlightsBean implements Serializable {

	private int flight_id;
	private String flight_number;
	private int departure_city_id;
	private int arrival_city_id;
	private Date departure_date;
	private Date arrival_date;
	private int aircraft_id;
	private int ticket_amount;
	private double ticket_price;

	private Flights flight;

	private double total_sum;
	private String search_flight_number;
	private Date search_departure_date;
	private int search_departure_city_id;
	private int search_arrival_city_id;
	private Date search_depart_date_start;
	private Date search_depart_date_end;
	private Date minSearchDate;
	private int search_qty=1;
	private List<Flights> userBasket = new ArrayList<Flights>();
	private List<Flights> filteredFlights;
	private String getDateString;
	private int itemsInBasket; 

	public String getGetDateString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM hh:ss");
		return dateFormat.format(date);
	}

	public void setGetDateString(String getDateString) {
		this.getDateString = getDateString;
	}

	@Inject
	private FlightsService flightsService;

	@Inject
	private FlightsListBean flightsListBean;

	@Inject
	private UsersBean usersBean;

	public FlightsBean() {
	}

	public List<Flights> getFilteredFlights() {
		return filteredFlights;
	}

	public void setFilteredFlights(List<Flights> filteredFlights) {
		this.filteredFlights = filteredFlights;
	}

	public Date getMinSearchDate() {
		return new Date();
	}

	public void setMinSearchDate(Date minSearchDate) {
		this.minSearchDate = minSearchDate;
	}

	public int getItemsInBasket() {
		itemsInBasket = 0;
		for (Flights f : userBasket) {
			itemsInBasket += f.getQty();
		}
		return itemsInBasket;
	}

	public void setItemsInBasket(int itemsInBasket) {
		this.itemsInBasket = itemsInBasket;
	}

	public double getTotal_sum() {
		total_sum = 0.0;
		for (Flights f : userBasket) {
			total_sum += f.getQty() * f.getTicket_price();
		}
		return total_sum;
	}

	public void setTotal_sum(double total_sum) {
		this.total_sum = total_sum;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}

	public int getDeparture_city_id() {
		return departure_city_id;
	}

	public void setDeparture_city_id(int departure_city_id) {
		this.departure_city_id = departure_city_id;
	}

	public int getArrival_city_id() {
		return arrival_city_id;
	}

	public void setArrival_city_id(int arrival_city_id) {
		this.arrival_city_id = arrival_city_id;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	public Date getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}

	public int getAircraft_id() {
		return aircraft_id;
	}

	public void setAircraft_id(int aircraft_id) {
		this.aircraft_id = aircraft_id;
	}

	public int getTicket_amount() {
		return ticket_amount;
	}

	public void setTicket_amount(int ticket_amount) {
		this.ticket_amount = ticket_amount;
	}

	public double getTicket_price() {
		return ticket_price;
	}

	public void setTicket_price(double ticket_price) {
		this.ticket_price = ticket_price;
	}

	public String getSearch_flight_number() {
		return search_flight_number;
	}

	public void setSearch_flight_number(String search_flight_number) {
		this.search_flight_number = search_flight_number;
	}

	public Date getSearch_departure_date() {
		return search_departure_date;
	}

	public void setSearch_departure_date(Date search_departure_date) {
		this.search_departure_date = search_departure_date;
	}

	public int getSearch_departure_city_id() {
		return search_departure_city_id;
	}

	public void setSearch_departure_city_id(int search_departure_city_id) {
		this.search_departure_city_id = search_departure_city_id;
	}

	public int getSearch_arrival_city_id() {
		return search_arrival_city_id;
	}

	public void setSearch_arrival_city_id(int search_arrival_city_id) {
		this.search_arrival_city_id = search_arrival_city_id;
	}

	public Date getSearch_depart_date_start() {
		return search_depart_date_start;
	}

	public void setSearch_depart_date_start(Date search_depart_date_start) {
		this.search_depart_date_start = search_depart_date_start;
	}

	public Date getSearch_depart_date_end() {
		return search_depart_date_end;
	}

	public void setSearch_depart_date_end(Date search_depart_date_end) {
		this.search_depart_date_end = search_depart_date_end;
	}

	public int getSearch_qty() {
		return search_qty;
	}

	public void setSearch_qty(int search_qty) {
		this.search_qty = search_qty;
	}
	
	public void incrementSearchQty() {
		if (search_qty<10){
			search_qty++;
		}
	}
	
	public void decrementSearchQty() {
		if (search_qty>1){
			search_qty--;
		}
	}

	public List<Flights> getUserBasket() {
		return userBasket;
	}

	public void setUserBasket(List<Flights> userBasket) {
		this.userBasket = userBasket;
	}

	public Flights getFlight() {
		Flights f = new Flights();
		f.setFlight_number(flight_number);
		f.setDeparture_city_id(departure_city_id);
		f.setArrival_city_id(arrival_city_id);
		f.setDeparture_date(new Timestamp(departure_date.getTime()));
		f.setArrival_date(new Timestamp(arrival_date.getTime()));
		f.setTicket_amount(ticket_amount);
		f.setTicket_price(ticket_price);
		f.setAircraft_id(aircraft_id);
		return f;
	}

	public String addFlightForm() {
		return "addFlight.xhtml?faces-redirect=true";
	}

	public String editRemoveFlights() {
		return "flights.xhtml?faces-redirect=true";
	}

	public String addFlight() {
		flight = getFlight();
		flightsService.addFlight(flight);
		setFlight_number("");
		;
		setDeparture_city_id(0);
		setArrival_city_id(0);
		setDeparture_date(null);
		setArrival_date(null);
		setTicket_amount(0);
		setTicket_price(0);
		setAircraft_id(0);
		return "flights.xhtml?faces-redirect=true";
	}

	public boolean isRemoved(Flights f) {
		return flightsService.isRemoved(f);
	}

	public String deleteFlight(Flights f) {
		flightsService.deleteFlight(f);
		;
		return "flights.xhtml?faces-redirect=true";
	}

	public String updateFlight(Flights f) {
		flightsService.updateFlight(f);
		return "flights.xhtml?faces-redirect=true";
	}

	public String searchFlightsForUser() {
		List<Flights> result = flightsService.searchFlightsForCustomer(
				search_depart_date_start, search_depart_date_end,
				search_departure_city_id, search_arrival_city_id, search_qty);
		if (result.isEmpty()) {
			return "noFlights.xhtml";
		} else {
			flightsListBean.setSearchFlightsForUser(result);
			return "search.xhtml?faces-redirect=true";
		}
	}

	public void getTop10Flights() {
		List<Flights> result = flightsService.getTop10Flights();
		flightsListBean.setTop10Flights(result);
	}

	public String addFlightToBasketUser(Flights f) {
		f.setQty(search_qty);
		userBasket.add(f);
		return "order.xhtml?faces-redirect=true";
	}

	public String deleteFlightFromBasket(Flights f) {
		userBasket.remove(f);
		return "order.xhtml?faces-redirect=true";
	}

	public void changeQty(ValueChangeEvent event) {
		total_sum = 0;
		int newValue = (int) event.getNewValue();
		Flights f = (Flights) ((UIInput) event.getSource()).getAttributes()
				.get("flight");
		System.out.println("newValue= " + newValue + " " + f.getFlight_id());
		f.setQty(newValue);
	}

	public String continueSearchingFlights() {
		setSearch_departure_city_id(0);
		setSearch_arrival_city_id(0);
		setSearch_depart_date_start(null);
		setSearch_depart_date_end(null);
		setSearch_qty(1);
		return "welcome.xhtml?faces-redirect=true";
	}

	public String userCheckout() {
		return "book.xhtml?faces-redirect=true";
	}

	public String addBestOffer(Flights f) {
		List<Flights> bestOffer = new ArrayList<Flights>();
		bestOffer.add(f);
		flightsListBean.setBestOffer(bestOffer);
		return "detail.xhtml";
	}

	public void onCellEdit(CellEditEvent event) {
	}
}
