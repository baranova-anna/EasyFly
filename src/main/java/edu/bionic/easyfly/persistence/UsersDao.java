package edu.bionic.easyfly.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class UsersDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void addUser(Users u){
	     em.persist(u);
	     em.flush();
		 em.clear();
	}
	
	public void deleteUser(Users u){
		em.createQuery("DELETE FROM Users u WHERE u.user_id = ?1")
				.setParameter(1, u.getUser_id())
				.executeUpdate();
	}
	
	public void updateUser(Users u){
		em.createQuery("UPDATE Users u SET u.user_login = ?1, u.user_password = ?2, "
				+ "u.user_email = ?3, u.user_position_id = ?4, u.changed = ?5 WHERE u.user_id = ?6")
				.setParameter(1, u.getUser_login())
				.setParameter(2, u.getUser_password())
				.setParameter(3, u.getUser_email())
				.setParameter(4, u.getUser_position_id())
				.setParameter(5, u.isChanged())
				.setParameter(6, u.getUser_id())
				.executeUpdate();
	}
	
	public List<Users> getAllUsersList(){
		TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u",
				Users.class);
		List<Users> users = null;
		users = query.getResultList();
		return users;
	}
	
	public Users authorize(String login, String password) { // authorization
		// for staff
		
		TypedQuery<Users> query = em
				.createQuery(
						"SELECT u FROM Users u WHERE u.user_login = ?1 AND u.user_password = ?2",
						Users.class);

		List<Users> u = null;
		query.setParameter(1, login);
		query.setParameter(2, password);
		u = query.getResultList();
		return CollectionUtils.isEmpty(u) ? null : u.get(0);
	}
}
