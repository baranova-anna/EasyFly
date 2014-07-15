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

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Named
@Scope("session")
public class LoginBean implements Serializable{
	
	private String login_enter;
	private String password_enter;
	private boolean isChanged;

	private Users user;
	
	@Inject
	private UsersService usersService;
	
	public LoginBean(){
	}  

	public String getLogin_enter() {
		return login_enter;
	}

	public void setLogin_enter(String login_enter) {
		this.login_enter = login_enter;
	}

	public String getPassword_enter() {
		return password_enter;
	}

	public void setPassword_enter(String password_enter) {
		this.password_enter = password_enter;
	}
	
	public boolean isChanged() {
		return isChanged;
	}

	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
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
	
	public String updatePassword(){
		user.setLoggedIn(true);
		user.setUser_password(passwordEncrypt(password_enter));
		user.setChanged(true);
		usersService.updateUser(user);
		return showStartPage();
	}
	
	public String authorize(){
		user = usersService.authorize(login_enter, passwordEncrypt(password_enter));
		if (user == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wrong login or password! Please, try again.", ""));
			return "login.xhtml";
		} else if (user.isChanged() == false){
			user.setLoggedIn(true);
			return setNewPassword();
		} else return showStartPage();
	}
	
	public String showStartPage(){	
		 if (user.getUser_position_id() == 1){
			user.setLoggedIn(true);
			return "flights.xhtml?faces-redirect=true";
		} else if (user.getUser_position_id() == 2){
			user.setLoggedIn(true);
			return "orders.xhtml?faces-redirect=true";
		} else if (user.getUser_position_id() == 3){
			user.setLoggedIn(true);
			return "reports.xhtml?faces-redirect=true";
		} else if (user.getUser_position_id() == 4){
			user.setLoggedIn(true);
			return "users.xhtml?faces-redirect=true";
		} else {
			return "login.xhtml?faces-redirect=true";
		}
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
	
	public String login(){
		return "login.xhtml?faces-redirect=true";
	}
	
	public String logout(){
		user.setLoggedIn(false);
		return "login.xhtml?faces-redirect=true";
	}
	
	public boolean isAuthorized(String pass){
		return user.isLoggedIn();
	}

}
