package org.ace.insurance.surveyquestion;

public class SurveyQuestionDTO {
	private String id;
	private String description;
	private InputType inputType;
	private String questionNo;

	public SurveyQuestionDTO(SurveyQuestion p) {
		this.id = p.getId();
		this.description = p.getDescription();
		this.inputType = p.getInputType();
		this.questionNo = p.getQuestionNo();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

}
