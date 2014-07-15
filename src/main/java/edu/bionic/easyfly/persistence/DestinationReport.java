package edu.bionic.easyfly.persistence;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.Transient;

import edu.bionic.easyfly.business.CitiesService;

public class DestinationReport {
	
	private int destination_city_id;
	private double total_sum;
	private long ticket_count;
	
	@Inject
	private CitiesService citiesService;
	
	public DestinationReport(int destination_city_id, long ticket_count, double total_sum){
		this.destination_city_id = destination_city_id;
		this.ticket_count = ticket_count;
		this.total_sum = total_sum;
	}

	public int getDestination_city_id() {
		return destination_city_id;
	}

	public void setDestination_city_id(int destination_city_id) {
		this.destination_city_id = destination_city_id;
	}

	public double getTotal_sum() {
		return total_sum;
	}

	public void setTotal_sum(double total_sum) {
		this.total_sum = total_sum;
	}

	public long getTicket_count() {
		return ticket_count;
	}

	public void setTicket_count(int ticket_count) {
		this.ticket_count = ticket_count;
	}
	
	public String getDestination_city(){
		return citiesService.getCityById(destination_city_id);
	}
}
