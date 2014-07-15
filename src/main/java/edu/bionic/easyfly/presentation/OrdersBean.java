package edu.bionic.easyfly.presentation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.OrdersService;
import edu.bionic.easyfly.persistence.DailyReport;
import edu.bionic.easyfly.persistence.DestinationReport;
import edu.bionic.easyfly.persistence.Flights;
import edu.bionic.easyfly.persistence.Orders;

@Named
@Scope("session")
public class OrdersBean {
	private int order_id;
	private int flight_id;
	private String first_name;
	private String last_name;
	private String order_email;
	private int ticket_amount;
	private double total_sum;
	private Timestamp book_date;
	private boolean sold_status;
	private int deleted_count;

	@Inject
	private ChartBean chartBean;

	private Orders order;

	private String search_last_name;
	private Date startDate;
	private Date endDate;
	List<DailyReport> dailyreport;
	List<DestinationReport> destinationreport;
	List<Orders> updateStatusList;
	List<Orders> filteredOrders;

	@Inject
	private OrdersService ordersService;

	@Inject
	private OrdersListBean ordersListBean;

	@Inject
	private FlightsBean flightsBean;

	@Inject
	private UsersBean usersBean;

	public List<Orders> getFilteredOrders() {
		return filteredOrders;
	}

	public void setFilteredOrders(List<Orders> filteredOrders) {
		this.filteredOrders = filteredOrders;
	}

	public List<DestinationReport> getDestinationreport() {
		return destinationreport;
	}

	public List<Orders> getUpdateStatusList() {
		return updateStatusList;
	}

	public void setUpdateStatusList(List<Orders> updateStatusList) {
		this.updateStatusList = updateStatusList;
	}

	public void setDestinationreport(List<DestinationReport> destinationreport) {
		this.destinationreport = destinationreport;
	}

	public List<DailyReport> getDailyreport() {
		return dailyreport;
	}

	public void setDailyreport(List<DailyReport> dailyreport) {
		this.dailyreport = dailyreport;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getOrder_email() {
		return order_email;
	}

	public void setOrder_email(String order_email) {
		this.order_email = order_email;
	}

	public int getTicket_amount() {
		return ticket_amount;
	}

	public void setTicket_amount(int ticket_amount) {
		this.ticket_amount = ticket_amount;
	}

	public double getTotal_sum() {
		return total_sum;
	}

	public void setTotal_sum(double total_sum) {
		this.total_sum = total_sum;
	}

	public Timestamp getBook_date() {
		return book_date;
	}

	public void setBook_date(Timestamp book_date) {
		this.book_date = book_date;
	}

	public boolean isSold_status() {
		return sold_status;
	}

	public void setSold_status(boolean sold_status) {
		this.sold_status = sold_status;
	}

	public String getSearch_last_name() {
		return search_last_name;
	}

	public void setSearch_last_name(String search_last_name) {
		this.search_last_name = search_last_name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getDeleted_count() {
		return deleted_count;
	}

	public void setDeleted_count(int deleted_count) {
		this.deleted_count = deleted_count;
	}

	public OrdersBean() {
	}

	public List<Orders> getOrder() {
		List<Orders> order = new ArrayList<Orders>();
		for (Flights f : flightsBean.getUserBasket()) {
			Orders o = new Orders();
			o.setFirst_name(first_name);
			o.setLast_name(last_name);
			o.setOrder_email(order_email);
			o.setFlight_id(f.getFlight_id());
			o.setBook_date(new Date());
			o.setSold_status(false);
			o.setTicket_amount(f.getQty());
			o.setTotal_sum(f.getQty() * f.getTicket_price());
			order.add(o);
		}
		flightsBean.setUserBasket(new ArrayList<Flights>());
		flightsBean.setSearch_departure_city_id(0);
		flightsBean.setSearch_arrival_city_id(0);
		flightsBean.setSearch_depart_date_start(null);
		flightsBean.setSearch_depart_date_end(null);
		flightsBean.setSearch_qty(1);
		return order;
	}

	public String addOrder() {
		List<Orders> order = getOrder();
		for (Orders o : order) {
			ordersService.addOrder(o);
		}

		return "orderSubmit.xhtml";
	}

	public String convertOrdertoSold(Orders o) {
		ordersService.convertOrdertoSold(o);
		return "orders.xhtml?faces-redirect=true";
	}

	public String makeDailyReport() {
		// if(!usersBean.getLoggedUser().isLoggedIn()){
		// return "login.xhtml?faces-redirect=true";
		// }
		dailyreport = ordersService.makeDailyReport(startDate, endDate);
		chartBean.createCategoryModel(dailyreport);
		return "dailyReport.xhtml?faces-redirect=true";
	}

	public String makeReportByDestination() {
		// if(!usersBean.getLoggedUser().isLoggedIn()){
		// return "login.xhtml?faces-redirect=true";
		// }
		destinationreport = ordersService.makeReportByDestination(startDate,
				endDate);
		chartBean.createPieModel(destinationreport);
		return "destinationReport.xhtml?faces-redirect=true";
	}

	public String convertTicketstoFree() {
		int count = ordersService.convertTicketstoFree();
		setDeleted_count(count);
		return "convert.xhtml?faces-redirect=true";
	}

	public String getPersonalUserData() {
		return "book.xhtml?faces-redirect=true";
	}

}
