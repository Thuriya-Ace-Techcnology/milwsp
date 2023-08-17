package org.ace.insurance.medical.surveyAnswer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.ClaimType;
import org.ace.insurance.common.SurveyType;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.surveyquestion.InputType;
import org.ace.java.component.idgen.service.IDInterceptor;

@Entity
@Table(name = TableName.SURVEYQUESTIONANSWER)
@TableGenerator(name = "SURVEYQUESTIONANSWER_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "SURVEYQUESTIONANSWER_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "SurveyQuestionAnswer.findAll", query = "SELECT l FROM SurveyQuestionAnswer l "),
		@NamedQuery(name = "SurveyQuestionAnswer.findById", query = "SELECT l FROM SurveyQuestionAnswer l WHERE l.id = :id"), })
@EntityListeners(IDInterceptor.class)
public class SurveyQuestionAnswer {

	@Transient
	private ResourceQuestionAnswer selectResQuesAns;
	@Transient
	private List<ResourceQuestionAnswer> selectResQuesAnsList;
	@Transient
	private boolean tureLabelValue;
	@Transient
	private Date answerDate;
	@Transient
	private String answer;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SURVEYQUESTIONANSWER_GEN")
	private String id;
	private String questionId;
	private String description;
	private String frontLabel;
	private String behindLabel;
	private String tureLabel;
	private String falseLabel;

	@Column(name = "REQUIRED")
	private boolean option;
	private boolean deleteFlag;
	private int priority;

	@Enumerated(EnumType.STRING)
	private InputType inputType;

	private String productProcessId;

	// @OneToOne
	// @JoinColumn(name = "PRODUCTPROCESSID")
	// private ProductProcess productProcess;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "surveyQuestionAnswer", orphanRemoval = true)
	private List<ResourceQuestionAnswer> resourceQuestionList;

	@Enumerated(EnumType.STRING)
	private SurveyType surveyType;

	@Enumerated(EnumType.STRING)
	private ClaimType claimType;

	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public SurveyQuestionAnswer() {
		super();
	}

	public SurveyQuestionAnswer(SurveyQuestionAnswerDTO surveyQuestionDTO) {
		this.deleteFlag = surveyQuestionDTO.isDeleteFlag();
		this.description = surveyQuestionDTO.getDescription();
		this.frontLabel = surveyQuestionDTO.getFrontLabel();
		this.behindLabel = surveyQuestionDTO.getBehindLabel();
		this.tureLabel = surveyQuestionDTO.getTureLabel();
		this.falseLabel = surveyQuestionDTO.getFalseLabel();
		this.inputType = surveyQuestionDTO.getInputType();
		this.questionId = surveyQuestionDTO.getQuestionId();
		this.id = surveyQuestionDTO.getId();
		if (inputType.equals(InputType.TEXT) || inputType.equals(InputType.NUMBER) || inputType.equals(InputType.DATE)) {
			ResourceQuestionAnswer answer = new ResourceQuestionAnswer();
			addResourceQuestionList(answer);
		}
		if (inputType.equals(InputType.BOOLEAN)) {
			ResourceQuestionAnswer answerTrue = new ResourceQuestionAnswer();
			ResourceQuestionAnswer answerFalse = new ResourceQuestionAnswer();
			addResourceQuestionList(answerTrue);
			addResourceQuestionList(answerFalse);
		}
		for (ResourceQuestionAnswerDTO resourceQuestion : surveyQuestionDTO.getResourceQuestionList()) {
			ResourceQuestionAnswer rqa = new ResourceQuestionAnswer(resourceQuestion);
			addResourceQuestionList(rqa);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ClaimType getClaimType() {
		return claimType;
	}

	public void setClaimType(ClaimType claimType) {
		this.claimType = claimType;
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

	public List<ResourceQuestionAnswer> getResourceQuestionList() {
		if (resourceQuestionList == null) {
			resourceQuestionList = new ArrayList<ResourceQuestionAnswer>();
		}
		return resourceQuestionList;
	}
//
	public void setResourceQuestionList(List<ResourceQuestionAnswer> resourceQuestionList) {
		this.resourceQuestionList = resourceQuestionList;
	}
//
	public void addResourceQuestionList(ResourceQuestionAnswer resourceAnswer) {
		resourceAnswer.setSurveyQuestionAnswer(this);
		getResourceQuestionList().add(resourceAnswer);
	}

	public SurveyType getSurveyType() {
		return surveyType;
	}

	public void setSurveyType(SurveyType surveyType) {
		this.surveyType = surveyType;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public ResourceQuestionAnswer getSelectResQuesAns() {
		return selectResQuesAns;
	}

	public void setSelectResQuesAns(ResourceQuestionAnswer selectResQuesAns) {
		this.selectResQuesAns = selectResQuesAns;
	}

	public List<ResourceQuestionAnswer> getSelectResQuesAnsList() {
		if (selectResQuesAnsList == null)
			selectResQuesAnsList = new ArrayList<ResourceQuestionAnswer>();
		return selectResQuesAnsList;
	}

	public void setSelectResQuesAnsList(List<ResourceQuestionAnswer> selectResQuesAnsList) {
		this.selectResQuesAnsList = selectResQuesAnsList;
	}

	public boolean isTureLabelValue() {
		return tureLabelValue;
	}

	public void setTureLabelValue(boolean tureLabelValue) {
		this.tureLabelValue = tureLabelValue;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

//	public String getAnswer() {
//		StringBuffer sb = new StringBuffer();
//		if (InputType.TEXT.equals(this.inputType) || InputType.NUMBER.equals(this.inputType) || InputType.DATE.equals(this.inputType)) {
//			if (getResourceQuestionList() != null && !getResourceQuestionList().isEmpty()) {
//				this.answer = getResourceQuestionList().get(0).getResult();
//			}
//		} else {
//			for (ResourceQuestionAnswer resQA : getResourceQuestionList()) {
//				if (resQA.getValue() == 1) {
//					if (sb.length() > 0) {
//						sb.append(",");
//					}
//					sb.append(resQA.getName());
//				}
//			}
//			this.answer = sb.toString();
//		}
//
//		if (ValidationUtil.isStringEmpty(this.answer)) {
//			if (InputType.NONE.equals(this.inputType)) {
//				this.answer = "";
//			} else if (InputType.TEXT.equals(this.inputType)) {
//				this.answer = "\" \"";
//			} else if (InputType.NUMBER.equals(this.inputType)) {
//				this.answer = "0";
//			} else if (InputType.DATE.equals(this.inputType)) {
//				this.answer = "No Answer";
//			} else {
//				this.answer = "No Answer";
//			}
//		}
//		return answer;
//	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((behindLabel == null) ? 0 : behindLabel.hashCode());
		result = prime * result + ((claimType == null) ? 0 : claimType.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + (deleteFlag ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((falseLabel == null) ? 0 : falseLabel.hashCode());
		result = prime * result + ((frontLabel == null) ? 0 : frontLabel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inputType == null) ? 0 : inputType.hashCode());
		result = prime * result + (option ? 1231 : 1237);
		result = prime * result + priority;

		result = prime * result + ((questionId == null) ? 0 : questionId.hashCode());
		result = prime * result + ((surveyType == null) ? 0 : surveyType.hashCode());
		result = prime * result + ((tureLabel == null) ? 0 : tureLabel.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyQuestionAnswer other = (SurveyQuestionAnswer) obj;
		if (behindLabel == null) {
			if (other.behindLabel != null)
				return false;
		} else if (!behindLabel.equals(other.behindLabel))
			return false;
		if (claimType != other.claimType)
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (version != other.version)
			return false;
		return true;
	}

}
