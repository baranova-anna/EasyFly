package edu.bionic.easyfly.presentation;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.CompaniesService;
import edu.bionic.easyfly.persistence.Companies;

@Named
@Scope("session")
public class CompaniesBean {
	
	List<Companies> companies = null;
	
	@Inject
	private CompaniesService companiesService;

	public List<Companies> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Companies> companies) {
		this.companies = companies;
	}
	
	public void refreshList(){
		companies = companiesService.getAllCompanies();
	}

}
