package jsfcourse.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_subject database table.
 * 
 */
@Entity
@Table(name="user_subject")
@NamedQuery(name="UserSubject.findAll", query="SELECT u FROM UserSubject u")
public class UserSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idU_S;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="idSubject")
	private Subject subject;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public UserSubject() {
	}

	public int getIdU_S() {
		return this.idU_S;
	}

	public void setIdU_S(int idU_S) {
		this.idU_S = idU_S;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}