package com.jsf.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subject database table.
 * 
 */
@Entity
@NamedQuery(name="Subject.findAll", query="SELECT s FROM Subject s")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSubject;

	@Column(name="subject_name")
	private String subjectName;

	//bi-directional many-to-one association to ClassSubject
	@OneToMany(mappedBy="subject")
	private List<ClassSubject> classSubjects;

	//bi-directional many-to-one association to GradeUserSubject
	@OneToMany(mappedBy="subject")
	private List<GradeUserSubject> gradeUserSubjects;

	//bi-directional many-to-one association to UserSubject
	@OneToMany(mappedBy="subject")
	private List<UserSubject> userSubjects;

	public Subject() {
	}

	public int getIdSubject() {
		return this.idSubject;
	}

	public void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public List<ClassSubject> getClassSubjects() {
		return this.classSubjects;
	}

	public void setClassSubjects(List<ClassSubject> classSubjects) {
		this.classSubjects = classSubjects;
	}

	public ClassSubject addClassSubject(ClassSubject classSubject) {
		getClassSubjects().add(classSubject);
		classSubject.setSubject(this);

		return classSubject;
	}

	public ClassSubject removeClassSubject(ClassSubject classSubject) {
		getClassSubjects().remove(classSubject);
		classSubject.setSubject(null);

		return classSubject;
	}

	public List<GradeUserSubject> getGradeUserSubjects() {
		return this.gradeUserSubjects;
	}

	public void setGradeUserSubjects(List<GradeUserSubject> gradeUserSubjects) {
		this.gradeUserSubjects = gradeUserSubjects;
	}

	public GradeUserSubject addGradeUserSubject(GradeUserSubject gradeUserSubject) {
		getGradeUserSubjects().add(gradeUserSubject);
		gradeUserSubject.setSubject(this);

		return gradeUserSubject;
	}

	public GradeUserSubject removeGradeUserSubject(GradeUserSubject gradeUserSubject) {
		getGradeUserSubjects().remove(gradeUserSubject);
		gradeUserSubject.setSubject(null);

		return gradeUserSubject;
	}

	public List<UserSubject> getUserSubjects() {
		return this.userSubjects;
	}

	public void setUserSubjects(List<UserSubject> userSubjects) {
		this.userSubjects = userSubjects;
	}

	public UserSubject addUserSubject(UserSubject userSubject) {
		getUserSubjects().add(userSubject);
		userSubject.setSubject(this);

		return userSubject;
	}

	public UserSubject removeUserSubject(UserSubject userSubject) {
		getUserSubjects().remove(userSubject);
		userSubject.setSubject(null);

		return userSubject;
	}

}