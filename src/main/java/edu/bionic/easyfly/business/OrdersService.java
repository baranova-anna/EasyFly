package edu.bionic.easyfly.business;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.bionic.easyfly.persistence.DailyReport;
import edu.bionic.easyfly.persistence.DestinationReport;
import edu.bionic.easyfly.persistence.Flights;
import edu.bionic.easyfly.persistence.Orders;
import edu.bionic.easyfly.persistence.OrdersDao;


@Named
public class OrdersService {
	
	@Inject
	private OrdersDao ordersDao;
	
	@Transactional
    public void addOrder(Orders o){
		ordersDao.addOrder(o);
    }
	
	public List<Orders> getAllOrders(){
		return ordersDao.getAllOrders();
	}
	
	@Transactional
	public void convertOrdertoSold(Orders o){
		ordersDao.convertOrdertoSold(o);
	}
	
	@Transactional
	public int convertTicketstoFree(){
		return ordersDao.convertTicketstoFree();
	}
	
	public List<DailyReport> makeDailyReport(Date startDate, Date endDate){
		return ordersDao.makeDailyReport(startDate, endDate);
	}
	
	public List<DestinationReport> makeReportByDestination(Date startDate, Date endDate){
		return ordersDao.makeReportByDestination(startDate, endDate);
	}

}
