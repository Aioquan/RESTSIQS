package com.restsiqs.Entities;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private String studentId;
	private String studentPassword;
	private String sex;
	private String identityCard;
	private String bankCard;
	private String academyId;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String studentId) {
		this.studentId = studentId;
	}

	/** full constructor */
	public Student(String studentId, String studentPassword, String sex,
			String identityCard, String bankCard, String academyId) {
		this.studentId = studentId;
		this.studentPassword = studentPassword;
		this.sex = sex;
		this.identityCard = identityCard;
		this.bankCard = bankCard;
		this.academyId = academyId;
	}

	// Property accessors

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public String getAcademyId() {
		return this.academyId;
	}

	public void setAcademyId(String academyId) {
		this.academyId = academyId;
	}

}