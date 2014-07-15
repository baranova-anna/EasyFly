package edu.bionic.easyfly.persistence;

import javax.persistence.*;

@Entity
public class Cities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int city_id;
	private String city_name;
	private String country_name;
	private String airport_code;
	
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
	
	public Cities(){
	}
	
}
