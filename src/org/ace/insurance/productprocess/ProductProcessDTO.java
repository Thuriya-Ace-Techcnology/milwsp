package org.ace.insurance.productprocess;

import java.util.List;

import org.ace.insurance.resourceQuestion.ResourceQuestionDTO;
import org.ace.insurance.surveyquestion.InputType;
import org.ace.insurance.surveyquestion.ResourceQuestion;

public class ProductProcessDTO {
	private String productName;
	private String processName;
	private String surveyQuestion;
	private String surveyQuestionId;
	private InputType inputType;
	private List<ResourceQuestionDTO> resourceQuestionDTOs;
	public ProductProcessDTO() {

	}

	public ProductProcessDTO(String productName, String processName, String surveyQuestion,String surveyQuestionId,InputType inputType) {
		this.productName = productName;
		this.processName = processName;
		this.surveyQuestion = surveyQuestion;
		this.surveyQuestionId = surveyQuestionId;
		this.inputType = inputType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(String surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	public String getSurveyQuestionId() {
		return surveyQuestionId;
	}

	public void setSurveyQuestionId(String surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public List<ResourceQuestionDTO> getResourceQuestionDTOs() {
		return resourceQuestionDTOs;
	}

	public void setResourceQuestionDTOs(List<ResourceQuestionDTO> resourceQuestionDTOs) {
		this.resourceQuestionDTOs = resourceQuestionDTOs;
	}
	
}
