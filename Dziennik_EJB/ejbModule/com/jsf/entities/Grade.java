package com.jsf.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grade database table.
 * 
 */
@Entity
@NamedQuery(name="Grade.findAll", query="SELECT g FROM Grade g")
public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGrade;

	@Column(name="grade_name")
	private int gradeName;

	@Column(name="grade_weight")
	private int gradeWeight;

	//bi-directional many-to-one association to GradeUserSubject
	@OneToMany(mappedBy="grade")
	private List<GradeUserSubject> gradeUserSubjects;

	public Grade() {
	}

	public int getIdGrade() {
		return this.idGrade;
	}

	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}

	public int getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(int gradeName) {
		this.gradeName = gradeName;
	}

	public int getGradeWeight() {
		return this.gradeWeight;
	}

	public void setGradeWeight(int gradeWeight) {
		this.gradeWeight = gradeWeight;
	}

	public List<GradeUserSubject> getGradeUserSubjects() {
		return this.gradeUserSubjects;
	}

	public void setGradeUserSubjects(List<GradeUserSubject> gradeUserSubjects) {
		this.gradeUserSubjects = gradeUserSubjects;
	}

	public GradeUserSubject addGradeUserSubject(GradeUserSubject gradeUserSubject) {
		getGradeUserSubjects().add(gradeUserSubject);
		gradeUserSubject.setGrade(this);

		return gradeUserSubject;
	}

	public GradeUserSubject removeGradeUserSubject(GradeUserSubject gradeUserSubject) {
		getGradeUserSubjects().remove(gradeUserSubject);
		gradeUserSubject.setGrade(null);

		return gradeUserSubject;
	}

}