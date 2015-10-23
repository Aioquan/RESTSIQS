package com.restsiqs.Entities;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	// Fields

	private String noticeId;
	private String noticeTitle;
	private String noticeContext;
	private String noticeOperator;
	private String academyId;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(String noticeId) {
		this.noticeId = noticeId;
	}

	/** full constructor */
	public Notice(String noticeId, String noticeTitle, String noticeContext,
			String noticeOperator, String academyId) {
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeContext = noticeContext;
		this.noticeOperator = noticeOperator;
		this.academyId = academyId;
	}

	// Property accessors

	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return this.noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContext() {
		return this.noticeContext;
	}

	public void setNoticeContext(String noticeContext) {
		this.noticeContext = noticeContext;
	}

	public String getNoticeOperator() {
		return this.noticeOperator;
	}

	public void setNoticeOperator(String noticeOperator) {
		this.noticeOperator = noticeOperator;
	}

	public String getAcademyId() {
		return this.academyId;
	}

	public void setAcademyId(String academyId) {
		this.academyId = academyId;
	}

}