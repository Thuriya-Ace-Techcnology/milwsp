package org.ace.insurance.medical.surveyAnswer;

public class ResourceQuestionAnswerDTO {

	private int value;
	private String result;
	private int version;
	private String name;
    private String id;
	// This is not include equal and hashcode method.
	// private SurveyQuestionAnswerDTO surveyQuestionAnswerDTO;

	public ResourceQuestionAnswerDTO() {
	}

	public ResourceQuestionAnswerDTO(String name) {
		this.name = name;
	}

	public ResourceQuestionAnswerDTO(ResourceQuestionAnswer rqanswer) {
		this.id = rqanswer.getId();
		this.value = rqanswer.getValue();
		this.result = rqanswer.getResult();
		this.name = rqanswer.getName();
	}
  
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getResult() {
		if (result == null || result.isEmpty()) {
			result = "";
		}
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
