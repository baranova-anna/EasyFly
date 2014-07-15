package edu.bionic.easyfly.persistence;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
public class Flights {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flight_id;
	private String flight_number;
	private int departure_city_id;
	private int arrival_city_id;
	private Timestamp departure_date;
	private Timestamp arrival_date;
	private int aircraft_id;
	private int ticket_amount;
	private double ticket_price;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "departure_city_id")
	private Cities city1;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "arrival_city_id")
	private Cities city2;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "aircraft_id")
	private Aircrafts aircraft;

	@Transient
	private boolean editable;

	@Transient
	private int company;

	@Transient
	private String fullCityName1;

	@Transient
	private String fullCityName2;

	@Transient
	private String aircraft_model;

	public String getAircraft_model() {
		return aircraft.getAircraft_model();
	}

	public void setAircraft_model(String aircraft_model) {
		this.aircraft_model = aircraft_model;
	}

	public String getFullCityName1() {
		return city1.getCountry_name() + ", " + city1.getAirport_code();
	}

	public void setFullCityName1(String fullCityName1) {
		this.fullCityName1 = fullCityName1;
	}

	public String getFullCityName2() {
		return city2.getCountry_name() + ", " + city2.getAirport_code();
	}

	public void setFullCityName2(String fullCityName2) {
		this.fullCityName2 = fullCityName2;
	}

	public int getCompany() {
		return aircraft.getCompany_id();
	}

	public void setCompany(int company) {
		this.company = company;
	}

	@Transient
	public boolean isEditable() {
		return editable;
	}

	@Transient
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Transient
	private int qty;

	@Transient
	private String dateForFilter;

	@Transient
	private String cityForFilter;

	public String getCityForFilter() {
		return cityForFilter;
	}

	public void setCityForFilter(String cityForFilter) {
		this.cityForFilter = cityForFilter;
	}

	public String getDateForFilter() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return dateFormat.format(departure_date);
	}

	public void setDateForFilter(String dateForFilter) {
		this.dateForFilter = dateForFilter;
	}

	@Transient
	public int getQty() {
		return qty;
	}

	@Transient
	public void setQty(int qty) {
		this.qty = qty;
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
		return new java.util.Date(departure_date.getTime());
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = new Timestamp(departure_date.getTime());
	}

	public Date getArrival_date() {
		return new java.util.Date(arrival_date.getTime());
	}

	public void setArrival_date(Date arrival_date) {
		this.arrival_date = new Timestamp(arrival_date.getTime());
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

	public Cities getCity1() {
		return city1;
	}

	public void setCity1(Cities city1) {
		this.city1 = city1;
	}

	public Cities getCity2() {
		return city2;
	}

	public void setCity2(Cities city2) {
		this.city2 = city2;
	}

	public Flights() {
	}
}
