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
			
			Query query = em.createQuery("SELECT s.subjectName FROM Class c JOIN c.classSubjects cs JOIN cs.subject s WHERE c.className = :className");
			query.setParameter("className", className);
			
			try {
			List<String> subjects = query.getResultList();
			return subjects;
			} catch (Exception e) {
				return null;
			}
		}
		
		public List<Integer> getGradesBySubject(String subjectName, String login){
			
			Query query = em.createQuery("SELECT g.gradeName FROM GradeUserSubject gus JOIN gus.grade g JOIN gus.subject s JOIN gus.user u WHERE s.subjectName = :subjectName AND u.login = :login");
			query.setParameter("subjectName", subjectName);
			query.setParameter("login", login);
			List<Integer> gradeNameList = query.getResultList();
			return gradeNameList;
		}
		
		public String getSubjectByLogin(String login){
			Query query = em.createQuery("SELECT s.subjectName FROM Subject s JOIN s.userSubjects us JOIN us.user u WHERE u.login = :login");
			query.setParameter("login", login);
			
			List<String> gradeNameList = query.getResultList();
			return gradeNameList.get(0);
		}
		
		public List<String> getClassBySubject(String subjectName) {
			Query query = em.createQuery("SELECT c.className FROM Class c JOIN c.classSubjects cs JOIN cs.subject s WHERE s.subjectName = :subjectName");
			query.setParameter("subjectName", subjectName);
			List<String> classNameList = query.getResultList();
			return classNameList;
		}
		
}
