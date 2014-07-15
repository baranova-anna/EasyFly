package edu.bionic.easyfly.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import edu.bionic.easyfly.persistence.Companies;
import edu.bionic.easyfly.persistence.CompaniesDao;

@Named
public class CompaniesService {

	@Inject
	private CompaniesDao companiesDao;

	public List<Companies> getAllCompanies() {
		return companiesDao.getAllCompanies();
	}

}
