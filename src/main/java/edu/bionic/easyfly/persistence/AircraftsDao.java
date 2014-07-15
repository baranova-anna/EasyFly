package edu.bionic.easyfly.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class AircraftsDao {

	@PersistenceContext
	private EntityManager em;

	public List<Aircrafts> getAllAircrafts() { // show all list of aircrafts
		TypedQuery<Aircrafts> query = em.createQuery(
				"SELECT a FROM Aircrafts a", Aircrafts.class);
		List<Aircrafts> aircrafts = null;
		aircrafts = query.getResultList();
		return aircrafts;
	}

	public Aircrafts getAircraftName(int id) {
		TypedQuery<Aircrafts> query = em.createQuery(
				"SELECT a FROM Aircrafts a WHERE a.aircraft_id =" + id,
				Aircrafts.class);
		Aircrafts aircraft = null;
		aircraft = query.getSingleResult();
		return aircraft;
	}

}
