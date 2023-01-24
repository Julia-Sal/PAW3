package com.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	
	public List<String> getRolesByLogin(User user) {//zwróć rolę poprzez login
		Query query = em.createQuery("SELECT r.roleName FROM Role r JOIN r.userRoles ur JOIN ur.user u WHERE u.login = :login", String.class);
		query.setParameter("login", user.getLogin());	
		return query.getResultList();
	}
	
	
	public User getUserFromDatabase(String login, String password) {//sprawdź czy takie dane umożliwiają zalogowanie, jeśli tak zwróć true
		User user = getUserByLogin(login);
		if (user != null && user.getPassword().equals(password)) {
			return user;
        }
		else{
        	return null;
        }
	}
	
	
	public List<String> getClassNameByUserID(int idUser){//znajdź klasy, które są przypisane do użytkownika.
		Query query = em.createQuery("SELECT c.className FROM Class c JOIN c.classUsers cu JOIN cu.user u WHERE u.idUser = :idUser", String.class);
		query.setParameter("idUser", idUser);
               
		List<String> classNameList = query.getResultList();
		return classNameList;		
	}
	
	
	public List<User> getUsersByRole(String role, String param) {//zwraca użytkowników o podanym wyszukiwaniu
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u JOIN u.userRoles ur JOIN ur.role r WHERE (lower(u.name) LIKE lower(concat('%',:param,'%')) OR lower(u.surname) LIKE lower(concat('%',:param,'%'))) AND r.roleName = :role", User.class);
        query.setParameter("role", role);
        query.setParameter("param", param);
        return query.getResultList();
    }
	
}