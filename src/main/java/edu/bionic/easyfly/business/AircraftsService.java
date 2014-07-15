package edu.bionic.easyfly.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import edu.bionic.easyfly.persistence.Aircrafts;
import edu.bionic.easyfly.persistence.AircraftsDao;
import edu.bionic.easyfly.persistence.Cities;
import edu.bionic.easyfly.persistence.CitiesDao;
import edu.bionic.easyfly.persistence.FlightsDao;

@Named
public class AircraftsService {
	
	@Inject
	private AircraftsDao aircraftsDao;
	
	public List<Aircrafts> getAllAircrafts(){
		return aircraftsDao.getAllAircrafts();
	}
	
	public Aircrafts getAircraftName(int id){
		return aircraftsDao.getAircraftName(id);
	}

}
