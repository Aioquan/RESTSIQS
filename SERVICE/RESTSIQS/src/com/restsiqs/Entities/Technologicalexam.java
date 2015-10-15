package com.restsiqs.Entities;

/**
 * Technologicalexam entity. @author MyEclipse Persistence Tools
 */

public class Technologicalexam implements java.io.Serializable {

	// Fields

	private String tid;
	private Student student;
	private String tname;
	private String tdate;
	private Double tsorce;

	// Constructors

	/** default constructor */
	public Technologicalexam() {
	}

	/** minimal constructor */
	public Technologicalexam(String tid) {
		this.tid = tid;
	}

	/** full constructor */
	public Technologicalexam(String tid, Student student, String tname,
			String tdate, Double tsorce) {
		this.tid = tid;
		this.student = student;
		this.tname = tname;
		this.tdate = tdate;
		this.tsorce = tsorce;
	}

	// Property accessors

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTdate() {
		return this.tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public Double getTsorce() {
		return this.tsorce;
	}

	public void setTsorce(Double tsorce) {
		this.tsorce = tsorce;
	}

}