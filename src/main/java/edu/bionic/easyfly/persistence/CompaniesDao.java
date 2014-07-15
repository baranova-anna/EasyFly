package edu.bionic.easyfly.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class CompaniesDao {

	@PersistenceContext
	private EntityManager em;

	public List<Companies> getAllCompanies() { // show all companies
		TypedQuery<Companies> query = em.createQuery(
				"SELECT c FROM Companies c", Companies.class);
		List<Companies> companies = null;
		companies = query.getResultList();
		return companies;
	}

}
