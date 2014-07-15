package edu.bionic.easyfly.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class PositionsDao {

	@PersistenceContext
	private EntityManager em;

	public List<Positions> getPositions() { // show list of positions for view
											// form

		TypedQuery<Positions> query = em.createQuery(
				"SELECT p FROM Positions p", Positions.class);
		List<Positions> positions = null;
		positions = query.getResultList();
		return positions;
	}

}
