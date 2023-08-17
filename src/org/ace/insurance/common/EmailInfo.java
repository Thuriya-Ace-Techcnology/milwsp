package org.ace.insurance.common;

public class EmailInfo {
	private String toEmail;
	private String subject;
	private String userName;
	private String textBody;
	private String filePath;
	private String ccEmail;
	public EmailInfo() {
		super();
	}

	public EmailInfo(String toEmail, String subject, String userName, String textBody, String filePath,String ccEmail) {
		super();
		this.toEmail = toEmail;
		this.subject = subject;
		this.userName = userName;
		this.textBody = textBody;
		this.filePath = filePath;
		this.ccEmail = ccEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTextBody() {
		return textBody;
	}

	public void setTextBody(String textBody) {
		this.textBody = textBody;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

}
