package com.jsf.entities;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the class_subject database table.
 * 
 */
@Entity
@Table(name="class_subject")
@NamedQuery(name="ClassSubject.findAll", query="SELECT c FROM ClassSubject c")
public class ClassSubject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idS_C;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="idSubject")
	private Subject subject;

	//bi-directional many-to-one association to Class
	@ManyToOne
	@JoinColumn(name="idClass")
	private Class clazz;

	public ClassSubject() {
	}

	public int getIdS_C() {
		return this.idS_C;
	}

	public void setIdS_C(int idS_C) {
		this.idS_C = idS_C;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Class getClazz() {
		return this.clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

}