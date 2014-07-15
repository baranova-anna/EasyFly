package edu.bionic.easyfly.persistence;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;


@Repository
public class FlightsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Flights> getAllFlights() { // show all existing flights (for administrator) 
		TypedQuery<Flights> query = em.createQuery("SELECT f FROM Flights f",
				Flights.class);
		List<Flights> allFlights = null;
		allFlights = query.getResultList();
		return allFlights;
	}
	
	public List<Flights> searchFlightsForCustomer(Date startDate, Date endDate,
			int departure_city, int arrival_city, int ticket_count) { // search flights for customer

		TypedQuery<Flights> query = em.createQuery("SELECT f FROM Flights f "
				+ "WHERE cast(f.departure_date as date) between :dateStart "
				+ "and :dateEnd AND f.departure_city_id= :departure_city "
				+ "AND f.arrival_city_id = :arrival_city AND f.ticket_amount>= :ticket_count "
				+ "ORDER BY f.ticket_price", Flights.class);
		query.setParameter("dateStart", startDate);
		query.setParameter("dateEnd", endDate);
		query.setParameter("departure_city", departure_city);
		query.setParameter("arrival_city", arrival_city);
		query.setParameter("ticket_count", ticket_count);
		List<Flights> properFlights = null;
		properFlights = query.getResultList();
		return properFlights;
	}
	
	public void addFlight(Flights f) { // add new flight (for admin)
		em.persist(f);
		em.flush();
		em.clear();
	}
	
	public void updateFlight(Flights f) { // update flight (for admin)
		em.createQuery(
				"UPDATE Flights f SET f.flight_number = ?1, f.departure_city_id = ?2, "
						+ "f.arrival_city_id = ?3, f.departure_date = ?4, f.arrival_date = ?5, "
						+ "f.aircraft_id = ?6, f.ticket_amount = ?7, f.ticket_price = ?8 "
						+ "WHERE f.flight_id = ?9")
				.setParameter(1, f.getFlight_number()).setParameter(2, f.getDeparture_city_id())
				.setParameter(3, f.getArrival_city_id()).setParameter(4, f.getDeparture_date())
				.setParameter(5, f.getArrival_date()).setParameter(6, f.getAircraft_id())
				.setParameter(7, f.getTicket_amount()).setParameter(8, f.getTicket_price())
				.setParameter(9, f.getFlight_id()).executeUpdate();
	}
	
	public void deleteFlight(Flights f) { // delete flight (for admin)
		em.createQuery(
						"DELETE FROM Flights f WHERE f.flight_id = ?1 AND (SELECT count(ord) from Orders ord where ord.flight_id = ?1) = 0")
				.setParameter(1, f.getFlight_id()).executeUpdate();
	}
	
	public boolean isRemoved(Flights f){
		TypedQuery<Long> query = em.createQuery("SELECT count(ord) from Orders ord where ord.flight_id = ?1", Long.class);
		query.setParameter(1, f.getFlight_id());
		long count = query.getSingleResult();
		if (count > 0){
			return false;
		} else return true;
	}
	
	public List<Flights> getCurrentFlights() { // search flights for current date (web-service)
		
		Date curDate = new Date();
		TypedQuery<Flights> query = em.createQuery(
				"SELECT f FROM Flights f where cast(f.departure_date as date) = :curDate", Flights.class);
		query.setParameter("curDate", curDate);
		List<Flights> currentFlights = null;
		currentFlights = query.getResultList();
		return currentFlights;
	}
	
	public List<Flights> getTop10Flights(){
		Date curDate = new Date();
		TypedQuery<Flights> query = em.createQuery(
				"SELECT f FROM Flights f where cast(f.departure_date as date) > :curDate ORDER BY f.ticket_price", Flights.class);
		query.setParameter("curDate", curDate);
		List<Flights> top10flights = null;
		top10flights = query.setMaxResults(10).getResultList();
		return top10flights;
	}

}
