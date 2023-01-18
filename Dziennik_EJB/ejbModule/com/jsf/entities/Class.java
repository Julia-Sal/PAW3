package com.jsf.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the class database table.
 * 
 */
@Entity
@NamedQuery(name="Class.findAll", query="SELECT c FROM Class c")
public class Class implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idClass;

	@Column(name="class_name")
	private String className;

	//bi-directional many-to-one association to ClassSubject
	@OneToMany(mappedBy="clazz")
	private List<ClassSubject> classSubjects;

	//bi-directional many-to-one association to ClassUser
	@OneToMany(mappedBy="clazz")
	private List<ClassUser> classUsers;

	public Class() {
	}

	public int getIdClass() {
		return this.idClass;
	}

	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<ClassSubject> getClassSubjects() {
		return this.classSubjects;
	}

	public void setClassSubjects(List<ClassSubject> classSubjects) {
		this.classSubjects = classSubjects;
	}

	public ClassSubject addClassSubject(ClassSubject classSubject) {
		getClassSubjects().add(classSubject);
		classSubject.setClazz(this);

		return classSubject;
	}

	public ClassSubject removeClassSubject(ClassSubject classSubject) {
		getClassSubjects().remove(classSubject);
		classSubject.setClazz(null);

		return classSubject;
	}

	public List<ClassUser> getClassUsers() {
		return this.classUsers;
	}

	public void setClassUsers(List<ClassUser> classUsers) {
		this.classUsers = classUsers;
	}

	public ClassUser addClassUser(ClassUser classUser) {
		getClassUsers().add(classUser);
		classUser.setClazz(this);

		return classUser;
	}

	public ClassUser removeClassUser(ClassUser classUser) {
		getClassUsers().remove(classUser);
		classUser.setClazz(null);

		return classUser;
	}

}