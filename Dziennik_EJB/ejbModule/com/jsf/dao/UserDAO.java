package com.jsf.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.SortOrder;

import com.jsf.entities.ClassUser;
import com.jsf.entities.Role;
import com.jsf.entities.User;
import com.jsf.entities.UserRole;
import com.jsf.entities.Class;

@Stateless
public class UserDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Inject
	UserDAO userDAO;
	
	public void addNewUserToDatabase(String name, String surname, String login, String password, String roleName, String className) {
		
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setActive((byte) 1);
         
        Role role = em.createQuery("SELECT r FROM Role r WHERE r.roleName = :roleName", Role.class)
                .setParameter("roleName", roleName)
                .getSingleResult();
        
        em.persist(user);
        
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        user.addUserRole(userRole);

        em.persist(userRole);
        
        Class clazz = em.createQuery("SELECT c FROM Class c WHERE c.className = :className", Class.class)
                .setParameter("className", className)
                .getSingleResult();
        
        ClassUser classUser = new ClassUser();
        classUser.setClazz(clazz);
        classUser.setUser(user);
        
        Date currentDate = Date.valueOf(LocalDate.now());
        classUser.setCUdate(currentDate);
        
        user.addClassUser(classUser);
        
        em.persist(classUser);
        
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
	
	public void changePassword(String newPassword, String login) {
		User user = userDAO.getUserByLogin(login);
		user.setPassword(newPassword);
	}
	
	public User getUserByLogin(String login) {
		try {   
				return em.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
		        .setParameter("login", login)
		        .getSingleResult();
		} catch (Exception e) {
				return null;
		}
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
	
	public List<User> getUsersByRole(String roleName, String param) {//zwraca użytkowników o podanym wyszukiwaniu
		
		if(roleName==null) {
			roleName = "student";
		}if(param==null) {param = "";}
		
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u JOIN u.userRoles ur JOIN ur.role r WHERE (lower(u.name) LIKE lower(concat('%',:param,'%')) OR lower(u.surname) LIKE lower(concat('%',:param,'%'))) AND r.roleName = :role", User.class);
		query.setParameter("role", roleName);
        query.setParameter("param", param);
        
        return query.getResultList();
    }
	
	public long countUsersByRole(String roleName, String variable) {
		
		
		TypedQuery<Long> countQuery = em.createQuery("SELECT COUNT(u) FROM User u JOIN u.userRoles ur JOIN ur.role r WHERE (lower(u.name) LIKE lower(concat('%',:param,'%')) OR lower(u.surname) LIKE lower(concat('%',:param,'%'))) AND r.roleName = :role", Long.class);
		countQuery.setParameter("role", roleName);
		countQuery.setParameter("param", variable);
		Long count = countQuery.getSingleResult();
        return count;
	}
	
	
	public List<User> getUsersByClassName(String className) {
		Query query = em.createQuery("SELECT cu.user FROM Class c JOIN c.classUsers cu WHERE c.className = :className")
		.setParameter("className", className);
		List<User> userList = query.getResultList();
		return userList;
		}
	
}