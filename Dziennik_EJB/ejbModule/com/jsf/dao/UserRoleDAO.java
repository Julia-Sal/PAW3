package com.jsf.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jsf.entities.UserRole;

@Stateless
public class UserRoleDAO {

	@PersistenceContext
	EntityManager em;
	
	public void insert(UserRole userRole) {
		em.persist(userRole);
	}

	public UserRole update(UserRole userRole) {
		return em.merge(userRole);
	}

	public void delete(UserRole userRole) {
		em.remove(em.merge(userRole));
	}

	public UserRole get(UserRole id) {
		return em.find(UserRole.class, id);
	}
}
