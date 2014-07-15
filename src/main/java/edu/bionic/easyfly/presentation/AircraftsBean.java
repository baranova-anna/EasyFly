package edu.bionic.easyfly.presentation;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.AircraftsService;
import edu.bionic.easyfly.persistence.Aircrafts;

@Named
@Scope("session")
public class AircraftsBean {

	List<Aircrafts> aircrafts = null;

	public List<Aircrafts> getAircrafts() {
		return aircrafts;
	}

	public void setAircrafts(List<Aircrafts> aircrafts) {
		this.aircrafts = aircrafts;
	}

	@Inject
	private AircraftsService aircraftsService;

	public void refreshList() {
		aircrafts = aircraftsService.getAllAircrafts();
	}

	public String getAircraftName(int id) {
		Aircrafts a = aircraftsService.getAircraftName(id);
		return a.getAircraft_model() + ", " + a.getBoard_number() + ", "
				+ a.getCompany().getCompany_name();
	}

}
