package org.ace.ws.model.mobileMedicalproposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Utils;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPerson;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPersonAddOn;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPersonBeneficiaries;
import org.ace.insurance.medical.surveyAnswer.SurveyQuestionAnswer;
import org.ace.insurance.medical.surveyAnswer.SurveyQuestionAnswerDTO;

public class MedicalProposalInsuredPersonDTO {

	private String id;
	private int age;
	private int unit;
	private double sumInsured;
	private double basicPremium;
	private double addOnPremium;

	private String insPersonCodeNo;

	private String inPersonGroupCodeNo;

	private String relationshipId;
	private String productId;
	private String initialId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String dateOfBirth;
	private String fatherName;
	private String idNo;
	private String occupationId;
	private String townshipId;
	private String address;
	private String phoneNo;
	private double height;
	private double weight;

	private IdType idType;

	private Gender gender;

	private List<MedicalBeneficiariesDTO> insuredPersonBeneficiariesList;
	private List<MedicalAddOnDTO> insuredPersonAddOnList;
	private List<SurveyQuestionAnswerDTO> surveyQuestionAnswerList;

	public MedicalProposalInsuredPersonDTO() {

	}

	public MedicalProposalInsuredPersonDTO(MobileMedicalProposalInsuredPerson insuredPerson) {

		this.id = insuredPerson.getId();
		this.age = insuredPerson.getAge();
		this.unit = insuredPerson.getUnit();
		this.sumInsured = insuredPerson.getSumInsured();
		this.basicPremium = insuredPerson.getBasicPremium();
		this.addOnPremium = insuredPerson.getAddOnPremium();
		this.insPersonCodeNo = insuredPerson.getInsPersonCodeNo();

		this.inPersonGroupCodeNo = insuredPerson.getInPersonGroupCodeNo();

		this.relationshipId = insuredPerson.getRelationshipId();
		this.productId = insuredPerson.getProductId();
		this.initialId = insuredPerson.getInitialId();
		this.firstName = insuredPerson.getFirstName();
		this.lastName = insuredPerson.getLastName();
		this.middleName = insuredPerson.getMiddleName();
		this.dateOfBirth = insuredPerson.getDateOfBirth()!= null ? Utils.getDateFormatString(insuredPerson.getDateOfBirth()) : null;
		this.fatherName = insuredPerson.getFatherName();
		this.idNo = insuredPerson.getIdNo();
		this.occupationId = insuredPerson.getOccupationId();
		this.townshipId = insuredPerson.getTownshipId();
		this.address = insuredPerson.getAddress();
		this.phoneNo = insuredPerson.getPhoneNo();
		this.height = insuredPerson.getHeight();
		this.weight = insuredPerson.getWeight();
		this.idType = insuredPerson.getIdType();
		this.gender = insuredPerson.getGender();

		if (insuredPerson.getInsuredPersonBeneficiariesList() != null) {
			insuredPersonBeneficiariesList = new ArrayList<MedicalBeneficiariesDTO>();
			for (MobileMedicalProposalInsuredPersonBeneficiaries mb001 : insuredPerson.getInsuredPersonBeneficiariesList()) {
				insuredPersonBeneficiariesList.add(new MedicalBeneficiariesDTO(mb001));
			}
		}

		if (insuredPerson.getInsuredPersonAddOnList() != null) {
			insuredPersonAddOnList = new ArrayList<>();
			for (MobileMedicalProposalInsuredPersonAddOn mipa001 : insuredPerson.getInsuredPersonAddOnList()) {
				insuredPersonAddOnList.add(new MedicalAddOnDTO(mipa001));
			}
		}
		if (insuredPerson.getSurveyQuestionAnswerList() != null) {
			surveyQuestionAnswerList = new ArrayList<>();
			for (SurveyQuestionAnswer mipa001 : insuredPerson.getSurveyQuestionAnswerList()) {
				surveyQuestionAnswerList.add(new SurveyQuestionAnswerDTO(mipa001));
			}
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public double getBasicPremium() {
		return basicPremium;
	}

	public void setBasicPremium(double basicPremium) {
		this.basicPremium = basicPremium;
	}

	public double getAddOnPremium() {
		return addOnPremium;
	}

	public void setAddOnPremium(double addOnPremium) {
		this.addOnPremium = addOnPremium;
	}

	public String getInsPersonCodeNo() {
		return insPersonCodeNo;
	}

	public void setInsPersonCodeNo(String insPersonCodeNo) {
		this.insPersonCodeNo = insPersonCodeNo;
	}

	public String getInPersonGroupCodeNo() {
		return inPersonGroupCodeNo;
	}

	public void setInPersonGroupCodeNo(String inPersonGroupCodeNo) {
		this.inPersonGroupCodeNo = inPersonGroupCodeNo;
	}

	public String getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}

	public String getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(String townshipId) {
		this.townshipId = townshipId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public IdType getIdType() {
		return idType;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<MedicalBeneficiariesDTO> getInsuredPersonBeneficiariesList() {
		return insuredPersonBeneficiariesList;
	}

	public void setInsuredPersonBeneficiariesList(List<MedicalBeneficiariesDTO> insuredPersonBeneficiariesList) {
		this.insuredPersonBeneficiariesList = insuredPersonBeneficiariesList;
	}

	public List<MedicalAddOnDTO> getInsuredPersonAddOnList() {
		return insuredPersonAddOnList;
	}

	public void setInsuredPersonAddOnList(List<MedicalAddOnDTO> insuredPersonAddOnList) {
		this.insuredPersonAddOnList = insuredPersonAddOnList;
	}

	public List<SurveyQuestionAnswerDTO> getSurveyQuestionAnswerList() {
		return surveyQuestionAnswerList;
	}

	public void setSurveyQuestionAnswerList(List<SurveyQuestionAnswerDTO> surveyQuestionAnswerList) {
		this.surveyQuestionAnswerList = surveyQuestionAnswerList;
	}

}
