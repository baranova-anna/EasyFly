package edu.bionic.easyfly.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import edu.bionic.easyfly.business.UsersService;
import edu.bionic.easyfly.persistence.Users;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Named
@Scope("session")
public class UsersBean {
	
	private int user_id;
	private String user_login;
	private String user_password;
	private String user_email;
	private int user_position_id;

	private Users user;
	
	private Users selectedUser;
	
	@Inject
	private UsersService usersService;
	
	@Inject
	private UsersListBean usersListBean;
	
	public UsersBean(){
	}  
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}
	
	public Users getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(Users selectedUser) {
		this.selectedUser = selectedUser;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_position_id() {
		return user_position_id;
	}

	public void setUser_position_id(int user_position_id) {
		this.user_position_id = user_position_id;
	}

	public Users getUser() {
		Users u = new Users();
		u.setUser_login(user_login);
		u.setUser_password(passwordEncrypt(user_password));
		u.setUser_email(user_email);
		u.setUser_position_id(user_position_id);
		u.setChanged(false);
		return u;
	}
	
	public String addUserForm(){
		return "addUser.xhtml?faces-redirect=true";
	}

	public String addUser() {
		user = getUser();
		usersService.addUser(user);
		setUser_login("");
		setUser_email("");
		setUser_password("");
		setUser_position_id(0);
		return "users.xhtml?faces-redirect=true";
	}
	
	public String deleteUser(Users u){
		usersService.deleteUser(u);	
		return "users.xhtml?faces-redirect=true";
	}
	
	
	public String updateUser(Users u){
		u.setUser_password(passwordEncrypt(u.getUser_password()));
		u.setChanged(false);
		usersService.updateUser(u);
		return "users.xhtml?faces-redirect=true";
	}
	
	public String setNewPassword(){
		return "setPassword.xhtml?faces-redirect=true";
	}
	
	public String passwordEncrypt(String password){
		String salt = "LongStringForExtraSecurity@#$!%^&*(*)1234567890";       
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update((password+salt).getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
		return encryptedPassword;
	}
	
	public String editUser(Users u){
		selectedUser = u;
		selectedUser.setUser_password("");
		return "editUser.xhtml?faces-redirect=true";
	}
	
	public String startUserPage(){
		return "welcome.xhtml?faces-redirect=true";
	}
	
	public String toUserBasket(){
		return "order.xhtml?faces-redirect=true";
	} 
	
	public String editRemoveUser(){
		return "users.xhtml?faces-redirect=true";
	}

}
