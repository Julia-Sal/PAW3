package com.jsf.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jsf.entities.Class;
import com.jsf.entities.ClassUser;
import com.jsf.entities.Grade;
import com.jsf.entities.GradeUserSubject;
import com.jsf.entities.Subject;
import com.jsf.entities.User;

@Stateless
public class GradeDAO {
	@PersistenceContext
	EntityManager em;
	
	@Inject
	UserDAO userDAO;
	
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
		
		public double getAVG(String subjectName, String login){
			try {
			Query query = em.createQuery("SELECT SUM(g.gradeName*g.gradeWeight) FROM GradeUserSubject gus JOIN gus.grade g JOIN gus.subject s JOIN gus.user u WHERE s.subjectName = :subjectName AND u.login = :login");
			query.setParameter("subjectName", subjectName);
			query.setParameter("login", login);
			
			Query query2 = em.createQuery("SELECT SUM(g.gradeWeight) FROM GradeUserSubject gus JOIN gus.grade g JOIN gus.subject s JOIN gus.user u WHERE s.subjectName = :subjectName AND u.login = :login");
			query2.setParameter("subjectName", subjectName);
			query2.setParameter("login", login);
			
			Long result= (Long) query.getSingleResult();
			Long result2= (Long) query2.getSingleResult();
			
			double finalResult = Double.valueOf(result)/Double.valueOf(result2);
			return finalResult;
			}catch(Exception e) {
				return 0;
			}
		}	
		
		public void	addGrade(int gradeValue, int gradeWeight, int studentID, String subjectName) {
			User user = userDAO.getUserById(studentID);	
			Grade grade = em.createQuery("SELECT g FROM Grade g WHERE g.gradeWeight = :gradeWeight AND g.gradeName = :gradeValue", Grade.class)
					.setParameter("gradeValue", gradeValue)
					.setParameter("gradeWeight", gradeWeight)
	                .getSingleResult();
			Subject subject = em.createQuery("SELECT s FROM Subject s WHERE s.subjectName = :subjectName", Subject.class)
					.setParameter("subjectName", subjectName)
	                .getSingleResult();
			
			GradeUserSubject gradeUserSubject  = new GradeUserSubject ();
			 	gradeUserSubject.setSubject(subject);;
		        gradeUserSubject.setUser(user);
		        gradeUserSubject.setGrade(grade);
		        gradeUserSubject.getDescription();
		        
		        em.persist(gradeUserSubject);
		}
		
		
}
