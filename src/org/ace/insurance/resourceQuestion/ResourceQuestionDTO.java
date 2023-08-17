package org.ace.insurance.resourceQuestion;

public class ResourceQuestionDTO {
	private String id;
	private String name;
	private String surveyQuestionId;
	
	
	public ResourceQuestionDTO() {
	}
	public ResourceQuestionDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurveyQuestionId() {
		return surveyQuestionId;
	}
	public void setSurveyQuestionId(String surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	
}
