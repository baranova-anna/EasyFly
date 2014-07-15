package edu.bionic.easyfly.presentation;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.UsersService;
import edu.bionic.easyfly.persistence.Users;

@Named
@Scope("session")
public class UsersListBean {

	private List<Users> users = null;
	@Inject
	private UsersService usersService;

	public UsersListBean() {
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public void refreshList() {
		users = usersService.getAllUsersList();
	}

}
