package edu.bionic.easyfly.presentation;

import java.util.Date;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.FlightsService;
import edu.bionic.easyfly.business.OrdersService;
import edu.bionic.easyfly.persistence.Flights;
import edu.bionic.easyfly.persistence.Orders;

@Named
@Scope("session")
public class OrdersListBean {
	
	private List<Orders> orders = null;
	private List<Orders> searchOrders = null;
	@Inject
	private OrdersService ordersService;
	
	public OrdersListBean(){   }
	
	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
	public List<Orders> getSearchOrders() {
		return searchOrders;
	}

	public void setSearchOrders(List<Orders> orders) {
		this.searchOrders = orders;
	}

	public void refreshList(){
		orders = ordersService.getAllOrders();
	}


}
