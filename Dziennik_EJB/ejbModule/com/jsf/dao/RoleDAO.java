package com.jsf.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jsf.entities.Role;

@Stateless
public class RoleDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void insert(Role role) {
		em.persist(role);
	}

	public Role update(Role role) {
		return em.merge(role);
	}

	public void delete(Role role) {
		em.remove(em.merge(role));
	}

	public Role get(Role id) {
		return em.find(Role.class, id);
	}
}
