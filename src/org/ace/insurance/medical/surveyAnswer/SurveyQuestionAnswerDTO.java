package org.ace.insurance.medical.surveyAnswer;

import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.common.ClaimType;
import org.ace.insurance.common.CommonDTO;
import org.ace.insurance.common.SurveyType;
import org.ace.insurance.surveyquestion.InputType;

public class SurveyQuestionAnswerDTO extends CommonDTO implements Comparable<Object> {
	private boolean option;
	private boolean deleteFlag;
	private int priority;
	private String questionId;
	private String description;
	private String frontLabel;
	private String behindLabel;
	private String tureLabel;
	private String falseLabel;
	private InputType inputType;
	private String productProcessId;
	private List<ResourceQuestionAnswerDTO> resourceQuestionList;

	private SurveyType surveyType;
	private ClaimType claimType;

	public SurveyQuestionAnswerDTO() {
		super();
	}

	public SurveyQuestionAnswerDTO(SurveyQuestionAnswer surveyQuestion) {
		this.deleteFlag = surveyQuestion.isDeleteFlag();
		this.description = surveyQuestion.getDescription();
		this.frontLabel = surveyQuestion.getFrontLabel();
		this.behindLabel = surveyQuestion.getBehindLabel();
		this.tureLabel = surveyQuestion.getTureLabel();
		this.falseLabel = surveyQuestion.getFalseLabel();
		this.inputType = surveyQuestion.getInputType();
		this.setId(surveyQuestion.getId());
		if (inputType.equals(InputType.TEXT) || inputType.equals(InputType.NUMBER) || inputType.equals(InputType.DATE)) {
			ResourceQuestionAnswerDTO answer = new ResourceQuestionAnswerDTO();
			addResourceQuestionList(answer);
		}
		if (inputType.equals(InputType.BOOLEAN)) {
			ResourceQuestionAnswerDTO answerTrue = new ResourceQuestionAnswerDTO();
			answerTrue.setName(surveyQuestion.getTureLabel());
			answerTrue.setValue(0);
			ResourceQuestionAnswerDTO answerFalse = new ResourceQuestionAnswerDTO();
			answerFalse.setName(surveyQuestion.getFalseLabel());
			answerFalse.setValue(1);
			addResourceQuestionList(answerTrue);
			addResourceQuestionList(answerFalse);
		}
		for (ResourceQuestionAnswer resourceQuestion : surveyQuestion.getResourceQuestionList()) {
			ResourceQuestionAnswerDTO rqa = new ResourceQuestionAnswerDTO(resourceQuestion);
			addResourceQuestionList(rqa);
		}
	}

	public boolean isOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFrontLabel() {
		return frontLabel;
	}

	public void setFrontLabel(String frontLabel) {
		this.frontLabel = frontLabel;
	}

	public String getBehindLabel() {
		return behindLabel;
	}

	public void setBehindLabel(String behindLabel) {
		this.behindLabel = behindLabel;
	}

	public String getTureLabel() {
		return tureLabel;
	}

	public void setTureLabel(String tureLabel) {
		this.tureLabel = tureLabel;
	}

	public String getFalseLabel() {
		return falseLabel;
	}

	public void setFalseLabel(String falseLabel) {
		this.falseLabel = falseLabel;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getProductProcessId() {
		return productProcessId;
	}

	public void setProductProcessId(String productProcessId) {
		this.productProcessId = productProcessId;
	}

	public List<ResourceQuestionAnswerDTO> getResourceQuestionList() {
		if (resourceQuestionList == null) {
			resourceQuestionList = new ArrayList<ResourceQuestionAnswerDTO>();
		}
		return resourceQuestionList;
	}

	public void setResourceQuestionList(List<ResourceQuestionAnswerDTO> resourceQuestionList) {
		this.resourceQuestionList = resourceQuestionList;
	}

	public void addResourceQuestionList(ResourceQuestionAnswerDTO resourceAnswerDTO) {
		 //resourceAnswerDTO.setSurveyQuestionAnswerDTO(this);
		getResourceQuestionList().add(resourceAnswerDTO);
	}

	/* public MedicalSurveyDTO getMedicalSurveyDTO() {
		 return medicalSurveyDTO;
	 }
	
	 public void setMedicalSurveyDTO(MedicalSurveyDTO medicalSurveyDTO) {
		 this.medicalSurveyDTO = medicalSurveyDTO;
	 }*/

	public SurveyType getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(SurveyType surveyType) {
		this.surveyType = surveyType;
	}

	public ClaimType getClaimType() {
		return claimType;
	}

	public void setClaimType(ClaimType claimType) {
		this.claimType = claimType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();

		result = prime * result + ((behindLabel == null) ? 0 : behindLabel.hashCode());
		result = prime * result + ((claimType == null) ? 0 : claimType.hashCode());
		result = prime * result + (deleteFlag ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((falseLabel == null) ? 0 : falseLabel.hashCode());
		result = prime * result + ((frontLabel == null) ? 0 : frontLabel.hashCode());
		result = prime * result + ((inputType == null) ? 0 : inputType.hashCode());

		result = prime * result + (option ? 1231 : 1237);
		result = prime * result + priority;

		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());

		result = prime * result + ((surveyType == null) ? 0 : surveyType.hashCode());
		result = prime * result + ((tureLabel == null) ? 0 : tureLabel.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyQuestionAnswerDTO other = (SurveyQuestionAnswerDTO) obj;

		if (behindLabel == null) {
			if (other.behindLabel != null)
				return false;
		} else if (!behindLabel.equals(other.behindLabel))
			return false;
		if (claimType != other.claimType)
			return false;
		if (deleteFlag != other.deleteFlag)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (falseLabel == null) {
			if (other.falseLabel != null)
				return false;
		} else if (!falseLabel.equals(other.falseLabel))
			return false;
		if (frontLabel == null) {
			if (other.frontLabel != null)
				return false;
		} else if (!frontLabel.equals(other.frontLabel))
			return false;
		if (inputType != other.inputType)
			return false;

		if (option != other.option)
			return false;
		if (priority != other.priority)
			return false;

		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;

		if (surveyType != other.surveyType)
			return false;
		if (tureLabel == null) {
			if (other.tureLabel != null)
				return false;
		} else if (!tureLabel.equals(other.tureLabel))
			return false;

		return true;
	}

	@Override
	public int compareTo(Object o) {
		SurveyQuestionAnswerDTO otherQueAnsDTO = (SurveyQuestionAnswerDTO) o;
		return this.priority - otherQueAnsDTO.priority;
	}

}
