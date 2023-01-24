package com.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jsf.entities.Role;
import com.jsf.entities.User;

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

	public Role get(int id) {
		return em.find(Role.class, id);
	}
	
}
