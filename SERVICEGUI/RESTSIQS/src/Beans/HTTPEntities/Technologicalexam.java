package Beans.HTTPEntities;

/**
 * Technologicalexam entity. @author MyEclipse Persistence Tools
 */

public class Technologicalexam implements java.io.Serializable {

	// Fields

	private String tid;
	private String tname;
	private String tdate;
	private Double tsorce;
	private String studentId;

	// Constructors

	/** default constructor */
	public Technologicalexam() {
	}

	/** minimal constructor */
	public Technologicalexam(String tid) {
		this.tid = tid;
	}

	/** full constructor */
	public Technologicalexam(String tid, String tname, String tdate,
			Double tsorce, String studentId) {
		this.tid = tid;
		this.tname = tname;
		this.tdate = tdate;
		this.tsorce = tsorce;
		this.studentId = studentId;
	}

	// Property accessors

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}