package edu.bionic.easyfly.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import edu.bionic.easyfly.persistence.Positions;
import edu.bionic.easyfly.persistence.PositionsDao;

@Named
public class PositionsService {

	@Inject
	private PositionsDao positionsDao;

	public List<Positions> getAllPositions() {
		return positionsDao.getPositions();
	}

}
