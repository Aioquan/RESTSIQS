package Beans.HTTPEntities;

/**
 * Course entity. @author MyEclipse Persistence Tools
 */

public class Course implements java.io.Serializable {

	// Fields

	private String courseId;
	private Double credit;
	private String teacherId;
	private String studentId;
	private String courseName;
	private String courseTime;
	private String courseDate;
	private Double test1;
	private Double test2;
	private Double test3;
	private Double exercises1;
	private Double exercises2;
	private Double exercises3;
	private Double exercises4;
	private Double exercises5;
	private Double finalTest;
	private Double dailyMark;
	private Double sum;

	// Constructors

	/** default constructor */
	public Course() {
	}

	/** minimal constructor */
	public Course(String courseId) {
		this.courseId = courseId;
	}

	/** full constructor */
	public Course(String courseId, Double credit, String teacherId,
			String studentId, String courseName, String courseTime,
			String courseDate, Double test1, Double test2, Double test3,
			Double exercises1, Double exercises2, Double exercises3,
			Double exercises4, Double exercises5, Double finalTest,
			Double dailyMark, Double sum) {
		this.courseId = courseId;
		this.credit = credit;
		this.teacherId = teacherId;
		this.studentId = studentId;
		this.courseName = courseName;
		this.courseTime = courseTime;
		this.courseDate = courseDate;
		this.test1 = test1;
		this.test2 = test2;
		this.test3 = test3;
		this.exercises1 = exercises1;
		this.exercises2 = exercises2;
		this.exercises3 = exercises3;
		this.exercises4 = exercises4;
		this.exercises5 = exercises5;
		this.finalTest = finalTest;
		this.dailyMark = dailyMark;
		this.sum = sum;
	}

	// Property accessors

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public Double getCredit() {
		return this.credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseTime() {
		return this.courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getCourseDate() {
		return this.courseDate;
	}

	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}

	public Double getTest1() {
		return this.test1;
	}

	public void setTest1(Double test1) {
		this.test1 = test1;
	}

	public Double getTest2() {
		return this.test2;
	}

	public void setTest2(Double test2) {
		this.test2 = test2;
	}

	public Double getTest3() {
		return this.test3;
	}

	public void setTest3(Double test3) {
		this.test3 = test3;
	}

	public Double getExercises1() {
		return this.exercises1;
	}

	public void setExercises1(Double exercises1) {
		this.exercises1 = exercises1;
	}

	public Double getExercises2() {
		return this.exercises2;
	}

	public void setExercises2(Double exercises2) {
		this.exercises2 = exercises2;
	}

	public Double getExercises3() {
		return this.exercises3;
	}

	public void setExercises3(Double exercises3) {
		this.exercises3 = exercises3;
	}

	public Double getExercises4() {
		return this.exercises4;
	}

	public void setExercises4(Double exercises4) {
		this.exercises4 = exercises4;
	}

	public Double getExercises5() {
		return this.exercises5;
	}

	public void setExercises5(Double exercises5) {
		this.exercises5 = exercises5;
	}

	public Double getFinalTest() {
		return this.finalTest;
	}

	public void setFinalTest(Double finalTest) {
		this.finalTest = finalTest;
	}

	public Double getDailyMark() {
		return this.dailyMark;
	}

	public void setDailyMark(Double dailyMark) {
		this.dailyMark = dailyMark;
	}

	public Double getSum() {
		return this.sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

}