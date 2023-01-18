package com.jsf.entities;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the class_user database table.
 * 
 */
@Entity
@Table(name="class_user")
@NamedQuery(name="ClassUser.findAll", query="SELECT c FROM ClassUser c")
public class ClassUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idC_U;

	private byte active;

	@Temporal(TemporalType.TIMESTAMP)
	private Date CUdate;

	//bi-directional many-to-one association to Class
	@ManyToOne
	@JoinColumn(name="idClass")
	private Class clazz;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public ClassUser() {
	}

	public int getIdC_U() {
		return this.idC_U;
	}

	public void setIdC_U(int idC_U) {
		this.idC_U = idC_U;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public Date getCUdate() {
		return this.CUdate;
	}

	public void setCUdate(Date CUdate) {
		this.CUdate = CUdate;
	}

	public Class getClazz() {
		return this.clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}