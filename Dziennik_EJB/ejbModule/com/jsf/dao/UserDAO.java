package com.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jsf.entities.User;

@Stateless
public class UserDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void insert(User user) {
		em.persist(user);
	}

	public User update(User user) {
		return em.merge(user);
	}

	public void delete(User user) {
		em.remove(em.merge(user));
	}

	public User getUserById(int id) {
		return em.find(User.class, id);
	}
	
	public User getUserByLogin(String login) {
		   return em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
		        .setParameter("login", login)
		        .getSingleResult();
		   
		}
	
	//zwróć rolę poprzez login
	public List<String> getRolesByLogin(User user) {
		
		Query query = em.createQuery("SELECT r.roleName FROM Role r JOIN r.userRoles ur JOIN ur.user u WHERE u.login = :login", String.class);
		query.setParameter("login", user.getLogin());	
		
		try {
		List<String> roles = query.getResultList();
		return roles;
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	//sprawdź czy takie dane umożliwiają zalogowanie, jeśli tak zwróć true
	public User getUserFromDatabase(String login, String password) {
		User user = getUserByLogin(login);
		if (user != null && user.getPassword().equals(password)) {
			return user;
        }
		else{
        	return null;
        }
	}
	
	//znajdź klasy, które są przypisane do użytkownika.
	public List<String> getClassNameByUserID(int idUser){
		Query query = em.createQuery("SELECT c.className FROM Class c JOIN c.classUsers cu JOIN cu.user u WHERE u.idUser = :idUser", String.class);
		query.setParameter("idUser", idUser);
               
		List<String> classNameList = query.getResultList();
		return classNameList;		
	}
	
}