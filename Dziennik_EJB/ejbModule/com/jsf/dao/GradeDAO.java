package com.jsf.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jsf.entities.Grade;

@Stateless
public class GradeDAO {
	@PersistenceContext
	EntityManager em;
	
	
	public void insert(Grade grade) {
		em.persist(grade);
	}

	public Grade update(Grade grade) {
		return em.merge(grade);
	}

	public void delete(Grade grade) {
		em.remove(em.merge(grade));
	}

	public Grade get(int id) {
		return em.find(Grade.class, id);
	}
	
	//pobierz dane do obliczenia średniej
	
	//znajdz przedmioty przynależne do klasy
		public List<String> getSubjectsByClassName(String className) {
			Query query = em.createQuery("SELECT s.subjectName FROM Subject s JOIN s.classSubjects cs JOIN cs.class c WHERE c.className = :className", String.class);
			query.setParameter("className", className);	
			
			try {
			List<Object> subjects = query.getResultList();
			System.out.println(subjects);
			return null;
			} catch (Exception e) {
				return null;
			}
		}
		
		public List<String> getGradesBySubject(String subjectName){
			return null;
		}
}
