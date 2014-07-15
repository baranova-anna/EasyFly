package edu.bionic.easyfly.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private String user_login;
	private String user_password;
	private String user_email;
	private int user_position_id;
	private boolean changed;

	@Transient
	private boolean editable;
	@Transient
	private boolean loggedIn;

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "user_position_id")
	private Positions positions;

	@Transient
	public boolean isLoggedIn() {
		return loggedIn;
	}

	@Transient
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
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

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	@Transient
	public boolean isEditable() {
		return editable;
	}

	@Transient
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Positions getPositions() {
		return positions;
	}

	public void setPositions(Positions value) {
		this.positions = value;
	}

	public Users() {
	}
}
