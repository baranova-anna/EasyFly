package edu.bionic.easyfly.presentation;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.CitiesService;
import edu.bionic.easyfly.business.FlightsService;
import edu.bionic.easyfly.persistence.Cities;

@Named
@Scope("session")
public class CitiesBean {
	
	private int city_id;
	private String city_name;
	private String country_name;
	private String airport_code;
	List<Cities> allCities = null;
	
	public List<Cities> getAllCities() {
		return allCities;
	}
	public void setAllCities(List<Cities> allCities) {
		this.allCities = allCities;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public String getAirport_code() {
		return airport_code;
	}
	public void setAirport_code(String airport_code) {
		this.airport_code = airport_code;
	}
	
	@Inject
	private CitiesService citiesService;
	
	public String getCityName(int id){
		return citiesService.getCityById(id);
	}
	
	public void refreshList(){
		allCities = citiesService.getAllCities();
	}

}
