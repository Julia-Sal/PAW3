package com.jsf.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;

	private byte active;

	private String login;

	private String name;

	private String password;

	private String surname;

	//bi-directional many-to-one association to ClassUser
	@OneToMany(mappedBy="user")
	private List<ClassUser> classUsers;

	//bi-directional many-to-one association to GradeUserSubject
	@OneToMany(mappedBy="user")
	private List<GradeUserSubject> gradeUserSubjects;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	//bi-directional many-to-one association to UserSubject
	@OneToMany(mappedBy="user")
	private List<UserSubject> userSubjects;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<ClassUser> getClassUsers() {
		return this.classUsers;
	}

	public void setClassUsers(List<ClassUser> classUsers) {
		this.classUsers = classUsers;
	}

	public ClassUser addClassUser(ClassUser classUser) {
		getClassUsers().add(classUser);
		classUser.setUser(this);

		return classUser;
	}

	public ClassUser removeClassUser(ClassUser classUser) {
		getClassUsers().remove(classUser);
		classUser.setUser(null);

		return classUser;
	}

	public List<GradeUserSubject> getGradeUserSubjects() {
		return this.gradeUserSubjects;
	}

	public void setGradeUserSubjects(List<GradeUserSubject> gradeUserSubjects) {
		this.gradeUserSubjects = gradeUserSubjects;
	}

	public GradeUserSubject addGradeUserSubject(GradeUserSubject gradeUserSubject) {
		getGradeUserSubjects().add(gradeUserSubject);
		gradeUserSubject.setUser(this);

		return gradeUserSubject;
	}

	public GradeUserSubject removeGradeUserSubject(GradeUserSubject gradeUserSubject) {
		getGradeUserSubjects().remove(gradeUserSubject);
		gradeUserSubject.setUser(null);

		return gradeUserSubject;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

	public List<UserSubject> getUserSubjects() {
		return this.userSubjects;
	}

	public void setUserSubjects(List<UserSubject> userSubjects) {
		this.userSubjects = userSubjects;
	}

	public UserSubject addUserSubject(UserSubject userSubject) {
		getUserSubjects().add(userSubject);
		userSubject.setUser(this);

		return userSubject;
	}

	public UserSubject removeUserSubject(UserSubject userSubject) {
		getUserSubjects().remove(userSubject);
		userSubject.setUser(null);

		return userSubject;
	}

}