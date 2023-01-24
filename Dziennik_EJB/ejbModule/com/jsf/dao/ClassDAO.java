package com.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ClassDAO {
	@PersistenceContext
	EntityManager em;
	
	public void insert(Class classVarible) {
		em.persist(classVarible);
	}

	public Class update(Class classVarible) {
		return em.merge(classVarible);
	}

	public void delete(Class classVarible) {
		em.remove(em.merge(classVarible));
	}

	public Class get(int id) {
		return em.find(Class.class, id);
	}
	
	//zwróć listę osób chodzących do tej klasy
	public List<String> getClassListName(String className) {
		Query queryN = em.createQuery("SELECT u.name FROM Class c JOIN c.classUsers cu JOIN cu.user u WHERE c.className = :className")
					.setParameter("className", className);
		 	List<String> userListNames = queryN.getResultList();  
		return userListNames;
	}
	
	public List<String> getClassListSurname(String className) {
		Query queryS = em.createQuery("SELECT u.surname FROM Class c JOIN c.classUsers cu JOIN cu.user u WHERE c.className = :className")
				.setParameter("className", className);
			List<String> userListSurnames = queryS.getResultList(); 
		return userListSurnames;
	}
	
    public List<String> findAllClassNames() {
        Query query = em.createQuery("SELECT c.className FROM Class c");
        return query.getResultList();
    }
    
    public List<String> findRoleNames() {
        Query query = em.createQuery("SELECT r.roleName FROM Role r where r.roleName != :adminRole");
        query.setParameter("adminRole", "admin");
        return query.getResultList();
    }
}
