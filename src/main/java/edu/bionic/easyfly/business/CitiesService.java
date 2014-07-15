package edu.bionic.easyfly.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import edu.bionic.easyfly.persistence.Cities;
import edu.bionic.easyfly.persistence.CitiesDao;

@Named
public class CitiesService {

	@Inject
	private CitiesDao citiesDao;

	public List<Cities> getAllCities() {
		return citiesDao.getAllCities();
	}

	public String getCityById(int id) {
		return citiesDao.getCityById(id);
	}

}
