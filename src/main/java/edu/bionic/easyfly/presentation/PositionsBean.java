package edu.bionic.easyfly.presentation;

import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.PositionsService;
import edu.bionic.easyfly.persistence.Positions;

@Named
@Scope("session")
public class PositionsBean {
	
	List<Positions> positions = null;
	
	@Inject
	private PositionsService positionsService;

	public List<Positions> getPositions() {
		return positions;
	}

	public void setPositions(List<Positions> positions) {
		this.positions = positions;
	}
	
	public void refreshList(){
		positions = positionsService.getAllPositions();
	}

}
