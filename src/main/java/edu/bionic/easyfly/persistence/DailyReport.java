package edu.bionic.easyfly.persistence;

import java.util.Date;

public class DailyReport {
	
	private Date book_date;
	private double total_sum;
	private long ticket_count;
	
	public DailyReport(Date book_date, long ticket_count, double total_sum){
		this.book_date = book_date;
		this.ticket_count = ticket_count;
		this.total_sum = total_sum;
	}
	
	public Date getBook_date() {
		return book_date;
	}

	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}

	public double getTotal_sum() {
		return total_sum;
	}

	public void setTotal_sum(double total_sum) {
		this.total_sum = total_sum;
	}

	public long getTicket_count() {
		return ticket_count;
	}

	public void setTicket_count(int ticket_count) {
		this.ticket_count = ticket_count;
	}
}
