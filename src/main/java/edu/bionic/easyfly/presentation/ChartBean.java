package edu.bionic.easyfly.presentation;
  
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.chart.CartesianChartModel;  
import org.primefaces.model.chart.ChartSeries;  
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import edu.bionic.easyfly.persistence.DailyReport;
import edu.bionic.easyfly.persistence.DestinationReport;

  
@Named
@Scope("session")
public class ChartBean implements Serializable {  
	
//	private static final long serialVersionUID = 1L;
  
    private CartesianChartModel categoryModelCount;  
    private CartesianChartModel categoryModelSum;  
    private PieChartModel pieModelCount;
    private PieChartModel pieModelSum;
    private List<DestinationReport> report;
  
    @Inject
    private CitiesBean citiesBean;
    
    public ChartBean() {  }  
    
    
    
    public List<DestinationReport> getReport() {
		return report;
	}

	public void setReport(List<DestinationReport> report) {
		this.report = report;
	}

	public CartesianChartModel getCategoryModelCount() {
		return categoryModelCount;
    }

	public void setCategoryModelCount(CartesianChartModel categoryModelCount) {
		this.categoryModelCount = categoryModelCount;
	}

	public CartesianChartModel getCategoryModelSum() {
		return categoryModelSum;
	}

	public void setCategoryModelSum(CartesianChartModel categoryModelSum) {
		this.categoryModelSum = categoryModelSum;
	}
	

	public PieChartModel getPieModelCount() {
		return pieModelCount;
	}

	public void setPieModelCount(PieChartModel pieModelCount) {
		this.pieModelCount = pieModelCount;
	}

	public PieChartModel getPieModelSum() {
		return pieModelSum;
	}

	public void setPieModelSum(PieChartModel pieModelSum) {
		this.pieModelSum = pieModelSum;
	}

	public void createCategoryModel(List<DailyReport> report) {  
        categoryModelCount = new CartesianChartModel();  
        categoryModelSum = new CartesianChartModel();
  
        ChartSeries count = new ChartSeries();  
        count.setLabel("Ticket count"); 
        
        for(DailyReport dr : report) {
        	count.set(dr.getBook_date(), dr.getTicket_count());
        }
  
        ChartSeries sum = new ChartSeries();  
        sum.setLabel("Total sum");  
        
        for(DailyReport dr : report) {
        	sum.set(dr.getBook_date(), dr.getTotal_sum());
        } 
  
        categoryModelCount.addSeries(count);  
        categoryModelSum.addSeries(sum);  
    }  
	
		public void createPieModel(List<DestinationReport> report){  
			setReport(report);
	        pieModelCount = new PieChartModel(); 
	        
	        for(DestinationReport dr : report){
	        	pieModelCount.set(citiesBean.getCityName(dr.getDestination_city_id()), dr.getTicket_count());
	        }
	        
	        pieModelSum = new PieChartModel(); 
	        
	        for(DestinationReport dr : report){
	        	pieModelSum.set(citiesBean.getCityName(dr.getDestination_city_id()), dr.getTotal_sum());
	        }
	        
		}

}  
