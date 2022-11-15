package jsfcourse.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the grade_user_subject database table.
 * 
 */
@Entity
@Table(name="grade_user_subject")
@NamedQuery(name="GradeUserSubject.findAll", query="SELECT g FROM GradeUserSubject g")
public class GradeUserSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idG_U_S;

	private String description;

	//bi-directional many-to-one association to Grade
	@ManyToOne
	@JoinColumn(name="idGrade")
	private Grade grade;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="idSubject")
	private Subject subject;

	public GradeUserSubject() {
	}

	public int getIdG_U_S() {
		return this.idG_U_S;
	}

	public void setIdG_U_S(int idG_U_S) {
		this.idG_U_S = idG_U_S;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}