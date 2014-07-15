package edu.bionic.easyfly.persistence;

import javax.persistence.*;

@Entity
public class Aircrafts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aircraft_id;
	private String board_number;
	private String aircraft_model;
	private int company_id;
	@ManyToOne
	@PrimaryKeyJoinColumn(name="company_id")
	private Companies company;
	@Transient
	private String aircraft_info;
	
	public String getAircraft_info() {
		return aircraft_model+ ", " + board_number +", "+company.getCompany_name();
	}
	public void setAircraft_info(String aircraft_info) {
		this.aircraft_info = aircraft_model+ ", " + board_number +", "+company.getCompany_name();
	}
	public Companies getCompany() {
		return company;
	}
	public void setCompany(Companies company) {
		this.company = company;
	}
	public int getAircraft_id() {
		return aircraft_id;
	}
	public void setAircraft_id(int aircraft_id) {
		this.aircraft_id = aircraft_id;
	}
	public String getBoard_number() {
		return board_number;
	}
	public void setBoard_number(String board_number) {
		this.board_number = board_number;
	}
	public String getAircraft_model() {
		return aircraft_model;
	}
	public void setAircraft_model(String aircraft_model) {
		this.aircraft_model = aircraft_model;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public Aircrafts(){
	}
}
