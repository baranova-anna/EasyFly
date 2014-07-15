package edu.bionic.easyfly.persistence;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class OrdersDao {

	@PersistenceContext
	private EntityManager em;

	public void addOrder(Orders o) { // add new flight (for admin)
		em.persist(o);
		em.createQuery(
				"UPDATE Flights f SET f.ticket_amount = f.ticket_amount - ?1 WHERE f.flight_id = ?2")
						.setParameter(1, o.getTicket_amount())
						.setParameter(2, o.getFlight_id()).executeUpdate();
	}

	public List<Orders> getAllOrders() { // get all updaid orders for Accountant

		TypedQuery<Orders> query = em.createQuery(
				"SELECT o FROM Orders o WHERE o.sold_status = 0", Orders.class);
		List<Orders> allOrders = null;
		allOrders = query.getResultList();
		return allOrders;
	}


	public void convertOrdertoSold(Orders o) {

			em.createQuery(
				"UPDATE Orders o SET o.sold_status = 1 WHERE o.order_id = ?1")
						.setParameter(1, o.getOrder_id()).executeUpdate();

	}

	public int convertTicketstoFree() { // convert all unpaid more than 3 days tickets

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -3);
		TypedQuery<Object[]> query = em
				.createQuery(
						"SELECT o.flight_id, sum(o.ticket_amount) FROM Orders o WHERE o.sold_status = 0 "
						+ "AND o.book_date < :book_date GROUP BY o.flight_id", Object[].class);
		query.setParameter("book_date", new Timestamp(c.getTimeInMillis()));
		List<Object[]> unpaidOrders = null;
		unpaidOrders = query.getResultList();
		if (unpaidOrders != null) {
			for (Object[] o : unpaidOrders) {
				em.createQuery(
						"UPDATE Flights f SET f.ticket_amount = f.ticket_amount + "
								+ o[1].toString() + " WHERE f.flight_id = "
								+ o[0].toString()).executeUpdate();
			}
		}
		int count = em.createQuery(
				"DELETE FROM Orders o WHERE o.sold_status = 0 AND o.book_date < ?1")
				.setParameter(1, new Timestamp(c.getTimeInMillis())).executeUpdate();
		return count;
	}
	
	public List<DailyReport> makeDailyReport(Date startDate, Date endDate){
		
		TypedQuery<DailyReport> query = em
		.createQuery(
				"SELECT new edu.bionic.easyfly.persistence.DailyReport(cast(o.book_date as date), sum(o.ticket_amount), sum(o.total_sum)) FROM Orders o WHERE o.sold_status = 1 AND cast(o.book_date as date) between :dateStart"
				+ " and :dateEnd GROUP BY cast(o.book_date as date)",
				DailyReport.class);
		List<DailyReport> report = null;
		query.setParameter("dateStart", startDate);
		query.setParameter("dateEnd", endDate);		
		report = query.getResultList();
		return report;
	}
	
	public List<DestinationReport> makeReportByDestination(Date startDate, Date endDate){
		
		TypedQuery<DestinationReport> query = em
				.createQuery(
						"SELECT new edu.bionic.easyfly.persistence.DestinationReport(f.arrival_city_id, sum(o.ticket_amount), sum(o.total_sum)) FROM Orders o, Flights f WHERE o.sold_status = 1 "
						+ "AND cast(o.book_date as date) between :dateStart AND :dateEnd AND o.flight_id = f.flight_id GROUP BY f.arrival_city_id",
						DestinationReport.class);
		query.setParameter("dateStart", startDate);
		query.setParameter("dateEnd", endDate);
		List<DestinationReport> reportByDestination = null;
		reportByDestination = query.getResultList();
		return reportByDestination;
	}

}
