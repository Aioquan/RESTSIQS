package com.restsiqs.Entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Academy entity. @author MyEclipse Persistence Tools
 */

public class Academy implements java.io.Serializable {

	// Fields

	private String academyId;
	private String academyName;
	private String academyAddress;
//	private Set students = new HashSet(0);
//	private Set teachers = new HashSet(0);

	// Constructors

	/** default constructor */
	public Academy() {
	}

	/** minimal constructor */
	public Academy(String academyId) {
		this.academyId = academyId;
	}

	/** full constructor */
	public Academy(String academyId, String academyName, String academyAddress) {
		this.academyId = academyId;
		this.academyName = academyName;
		this.academyAddress = academyAddress;
//		this.students = students;
//		this.teachers = teachers;
	}

	// Property accessors

	public String getAcademyId() {
		return this.academyId;
	}

	public void setAcademyId(String academyId) {
		this.academyId = academyId;
	}

	public String getAcademyName() {
		return this.academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getAcademyAddress() {
		return this.academyAddress;
	}

	public void setAcademyAddress(String academyAddress) {
		this.academyAddress = academyAddress;
	}

//	public Set getStudents() {
//		return this.students;
//	}
//
//	public void setStudents(Set students) {
//		this.students = students;
//	}
//
//	public Set getTeachers() {
//		return this.teachers;
//	}
//
//	public void setTeachers(Set teachers) {
//		this.teachers = teachers;
//	}

}