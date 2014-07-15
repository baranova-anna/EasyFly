package edu.bionic.easyfly.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


@Repository
public class CitiesDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Cities> getAllCities(){ //Show list of all cities for view form
		
		TypedQuery<Cities> query = em.createQuery(
				"SELECT c FROM Cities c", Cities.class);
		List<Cities> cities = null;
		cities = query.getResultList();
		return cities;
	}
	
	public String getCityById(int id){ //Show list of all cities for view form
		
		TypedQuery<String> query = em.createQuery(
				"SELECT c.city_name FROM Cities c WHERE c.city_id = ?1", String.class);
		query.setParameter(1, id);
		String city = null;
		city = query.getSingleResult();
		return city;
	}

}
