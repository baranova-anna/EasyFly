package edu.bionic.easyfly.business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.bionic.easyfly.persistence.Users;
import edu.bionic.easyfly.persistence.UsersDao;

@Named
public class UsersService {
	
	@Inject
	private UsersDao usersDao;
	
	public List<Users> getAllUsersList(){
    	return usersDao.getAllUsersList();
    }
	
	@Transactional
    public void addUser(Users u){
    	usersDao.addUser(u);
    }
	
	@Transactional
	public void updateUser(Users u){
		usersDao.updateUser(u);
	}
	
	@Transactional
	public void deleteUser(Users u){
		usersDao.deleteUser(u);
	}
	
	public Users authorize(String login, String password){
		Users u = usersDao.authorize(login, password);
		return u;
	}

}
