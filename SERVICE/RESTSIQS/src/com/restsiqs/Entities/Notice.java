package com.restsiqs.Entities;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	// Fields

	private String noticeId;
	private String academyId;
	private String noticeTitle;
	private String noticeContext;
	private String noticeOperator;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(String noticeId) {
		this.noticeId = noticeId;
	}

	/** full constructor */
	public Notice(String noticeId, String academyId, String noticeTitle,
			String noticeContext, String noticeOperator) {
		this.noticeId = noticeId;
		this.academyId = academyId;
		this.noticeTitle = noticeTitle;
		this.noticeContext = noticeContext;
		this.noticeOperator = noticeOperator;
	}

	// Property accessors

	public String getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getAcademyId() {
		return this.academyId;
	}

	public void setAcademyId(String academyId) {
		this.academyId = academyId;
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

}