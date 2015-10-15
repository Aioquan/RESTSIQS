package com.restsiqs.Entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private String studentId;
	private Academy academy;
	private String studentPassword;
	private String sex;
	private String identityCard;
	private String bankCard;
//	private Set courses = new HashSet(0);
//	private Set technologicalexams = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String studentId) {
		this.studentId = studentId;
	}

	/** full constructor */
	public Student(String studentId, Academy academy, String studentPassword,
			String sex, String identityCard, String bankCard) {
		this.studentId = studentId;
		this.academy = academy;
		this.studentPassword = studentPassword;
		this.sex = sex;
		this.identityCard = identityCard;
		this.bankCard = bankCard;
//		this.courses = courses;
//		this.technologicalexams = technologicalexams;
	}

	// Property accessors

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Academy getAcademy() {
		return this.academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public String getStudentPassword() {
		return this.studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityCard() {
		return this.identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

//	public Set getCourses() {
//		return this.courses;
//	}
//
//	public void setCourses(Set courses) {
//		this.courses = courses;
//	}
//
//	public Set getTechnologicalexams() {
//		return this.technologicalexams;
//	}
//
//	public void setTechnologicalexams(Set technologicalexams) {
//		this.technologicalexams = technologicalexams;
//	}

}