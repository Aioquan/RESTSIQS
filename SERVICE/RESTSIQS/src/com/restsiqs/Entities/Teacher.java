package com.restsiqs.Entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private String teacherId;
	private Academy academy;
	private String teacherName;
	private String teacherDepartment;
	private String teacherStatus;
//	private Set courses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(String teacherId) {
		this.teacherId = teacherId;
	}

	/** full constructor */
	public Teacher(String teacherId, Academy academy, String teacherName,
			String teacherDepartment, String teacherStatus) {
		this.teacherId = teacherId;
		this.academy = academy;
		this.teacherName = teacherName;
		this.teacherDepartment = teacherDepartment;
		this.teacherStatus = teacherStatus;
//		this.courses = courses;
	}

	// Property accessors

	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public Academy getAcademy() {
		return this.academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherDepartment() {
		return this.teacherDepartment;
	}

	public void setTeacherDepartment(String teacherDepartment) {
		this.teacherDepartment = teacherDepartment;
	}

	public String getTeacherStatus() {
		return this.teacherStatus;
	}

	public void setTeacherStatus(String teacherStatus) {
		this.teacherStatus = teacherStatus;
	}

//	public Set getCourses() {
//		return this.courses;
//	}
//
//	public void setCourses(Set courses) {
//		this.courses = courses;
//	}

}