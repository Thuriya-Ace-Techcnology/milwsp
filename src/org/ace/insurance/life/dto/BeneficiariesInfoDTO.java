package org.ace.insurance.life.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ace.insurance.common.AbstractMynNumConvertor;
import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Name;
import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.life.dao.entities.InsuredPersonBeneficiaries;
import org.ace.insurance.life.dao.entities.PolicyInsuredPersonBeneficiaries;
import org.ace.insurance.life.enums.IdConditionTypeMM;
import org.ace.insurance.system.common.relationship.RelationShip;

public class BeneficiariesInfoDTO {
	private float percentage;
	private Name name;
	private String beneName;
	private Gender gender;	
	private IdType idType;	
	private String fullIdNo;
	private ResidentAddress residentAddress;
	private String beneResidentAddress;
	private int version;
	private String relationshipId;
	private RelationShip relationShip;
	private String relationShipName;
	public BeneficiariesInfoDTO(InsuredPersonBeneficiaries beneficiary) {
		this.beneName = beneficiary.getName().getFirstName();
		this.percentage = beneficiary.getPercentage();
		this.idType = beneficiary.getIdType();
		this.fullIdNo = beneficiary.getIdNo();
		this.gender = beneficiary.getGender();
		this.beneResidentAddress = beneficiary.getResidentAddress().getResidentAddress();		
		this.relationshipId = beneficiary.getRelationship().getId();
		this.relationShipName = beneficiary.getRelationship().getName();

	}

	public BeneficiariesInfoDTO(PolicyInsuredPersonBeneficiaries policyInsuredPersonBeneficiaries) {
		this.percentage = policyInsuredPersonBeneficiaries.getPercentage();
		this.gender = policyInsuredPersonBeneficiaries.getGender();
		this.idType = policyInsuredPersonBeneficiaries.getIdType();
		this.fullIdNo = policyInsuredPersonBeneficiaries.getIdNo();
		this.residentAddress = policyInsuredPersonBeneficiaries.getResidentAddress();
		this.name = policyInsuredPersonBeneficiaries.getName();
		this.relationshipId = policyInsuredPersonBeneficiaries.getRelationship().getId();

	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	/*
	 * public void setCustomer(Customer customer) { this.customer = customer;
	 * if(customer != null) { Date dateOfBirth = customer.getDateOfBirth();
	 * Calendar cal_1 = Calendar.getInstance(); int currentYear =
	 * cal_1.get(Calendar.YEAR); Calendar cal_2 = Calendar.getInstance();
	 * cal_2.setTime(dateOfBirth); cal_2.set(Calendar.YEAR, currentYear); if(new
	 * Date().after(cal_2.getTime())) { Calendar cal_3 = Calendar.getInstance();
	 * cal_3.setTime(dateOfBirth); int year_1 = cal_3.get(Calendar.YEAR); int
	 * year_2 = cal_1.get(Calendar.YEAR) + 1; age = year_2 - year_1; } else {
	 * Calendar cal_3 = Calendar.getInstance(); cal_3.setTime(dateOfBirth); int
	 * year_1 = cal_3.get(Calendar.YEAR); int year_2 = cal_1.get(Calendar.YEAR);
	 * age = year_2 - year_1; } } }
	 */
	public String getRelationship() {
		return relationshipId;
	}

	public void setRelationship(String relationshipId) {
		this.relationshipId = relationshipId;
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

	public ResidentAddress getResidentAddress() {
		if (residentAddress == null) {
			residentAddress = new ResidentAddress();
		}
		return residentAddress;
	}

	public void setResidentAddress(ResidentAddress residentAddress) {
		this.residentAddress = residentAddress;
	}

	public Name getName() {
		if (name == null) {
			name = new Name();
		}
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


	public String getFullIdNo() {
		return fullIdNo;
	}

	public void setFullIdNo(String fullIdNo) {
		this.fullIdNo = fullIdNo;
	}



	





	
	public String getBeneName() {
		return beneName;
	}

	public void setBeneName(String beneName) {
		this.beneName = beneName;
	}
	

	public String getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(String relationshipId) {
		this.relationshipId = relationshipId;
	}

	public RelationShip getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(RelationShip relationShip) {
		this.relationShip = relationShip;
	}
	

	public String getBeneResidentAddress() {
		return beneResidentAddress;
	}

	public void setBeneResidentAddress(String beneResidentAddress) {
		this.beneResidentAddress = beneResidentAddress;
	}

	public String getRelationShipName() {
		return relationShipName;
	}

	public void setRelationShipName(String relationShipName) {
		this.relationShipName = relationShipName;
	}
	
	

	


}
