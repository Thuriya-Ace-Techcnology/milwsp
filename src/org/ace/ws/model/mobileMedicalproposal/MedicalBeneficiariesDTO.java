package org.ace.ws.model.mobileMedicalproposal;

import java.util.Date;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Utils;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPersonBeneficiaries;

public class MedicalBeneficiariesDTO {
	private String id;

	private String beneficiaryNo;
	private String initialId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fatherName;

	private String townshipId;
	private String address;
	private String phoneNo;
	private float percentage;
	private String relationshipId;

	private String dateOfBirth;

	private Gender gender;

	private IdType idType;

	private String idNo;

	public MedicalBeneficiariesDTO() {

	}

	public MedicalBeneficiariesDTO(MobileMedicalProposalInsuredPersonBeneficiaries beneficiary) {
		this.id = beneficiary.getId();
		this.beneficiaryNo = beneficiary.getBeneficiaryNo();
		this.initialId = beneficiary.getInitialId();
		this.firstName = beneficiary.getFirstName();
		this.lastName = beneficiary.getLastName();
		this.middleName = beneficiary.getMiddleName();
		this.fatherName = beneficiary.getFatherName();
		this.townshipId = beneficiary.getTownshipId();
		this.address = beneficiary.getAddress();
		this.phoneNo = beneficiary.getPhoneNo();
		this.percentage = beneficiary.getPercentage();
		this.relationshipId = beneficiary.getRelationshipId();
		this.dateOfBirth = beneficiary.getDateOfBirth()!= null ? Utils.getDateFormatString(beneficiary.getDateOfBirth()) : null;
		this.gender = beneficiary.getGender();
		this.idType = beneficiary.getIdType();
		this.idNo = beneficiary.getIdNo();

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBeneficiaryNo() {
		return beneficiaryNo;
	}

	public void setBeneficiaryNo(String beneficiaryNo) {
		this.beneficiaryNo = beneficiaryNo;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
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

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}

	

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public IdType getIdType() {
		return idType;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

}
