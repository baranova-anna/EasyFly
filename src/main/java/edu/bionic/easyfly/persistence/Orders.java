package edu.bionic.easyfly.persistence;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	private int flight_id;
	private String first_name;
	private String last_name;
	private String order_email;
	private int ticket_amount;
	private double total_sum;
	private Timestamp book_date;
	private boolean sold_status;
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "flight_id")
	private Flights flight;

	@Transient
	private boolean editable;

	@Transient
	public boolean isEditable() {
		return editable;
	}

	@Transient
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public void setBook_date(Date book_date) {
		this.book_date = new Timestamp(book_date.getTime());
	}

	public Date getBook_date() {
		return new java.util.Date(book_date.getTime());
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getOrder_email() {
		return order_email;
	}

	public void setOrder_email(String order_email) {
		this.order_email = order_email;
	}

	public int getTicket_amount() {
		return ticket_amount;
	}

	public void setTicket_amount(int ticket_amount) {
		this.ticket_amount = ticket_amount;
	}

	public double getTotal_sum() {
		return total_sum;
	}

	public void setTotal_sum(double total_sum) {
		this.total_sum = total_sum;
	}

	public boolean isSold_status() {
		return sold_status;
	}

	public void setSold_status(boolean sold_status) {
		this.sold_status = sold_status;
	}

	public Flights getFlights() {
		return flight;
	}

	public void setFlights(Flights value) {
		this.flight = value;
	}

	public Orders() {
	}

}
