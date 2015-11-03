package Beans.HTTPEntities;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private String teacherId;
	private String teacherName;
	private String teacherDepartment;
	private String teacherStatus;
	private String academyId;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(String teacherId) {
		this.teacherId = teacherId;
	}

	/** full constructor */
	public Teacher(String teacherId, String teacherName,
			String teacherDepartment, String teacherStatus, String academyId) {
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherDepartment = teacherDepartment;
		this.teacherStatus = teacherStatus;
		this.academyId = academyId;
	}

	// Property accessors

	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
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

	public String getAcademyId() {
		return this.academyId;
	}

	public void setAcademyId(String academyId) {
		this.academyId = academyId;
	}

}