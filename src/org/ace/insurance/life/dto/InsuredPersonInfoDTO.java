package org.ace.insurance.life.dto;

import java.util.*;


import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Name;
import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.common.plans.Plans;

import org.ace.insurance.life.dao.entities.InsuredPersonBeneficiaries;
import org.ace.insurance.life.dao.entities.InsuredPersonKeyFactorValue;


import org.ace.insurance.life.dao.entities.PolicyInsuredPerson;

import org.ace.insurance.life.dao.entities.PolicyInsuredPersonBeneficiaries;
import org.ace.insurance.life.dao.entities.PolicyInsuredPersonKeyFactorValue;
import org.ace.insurance.life.dao.entities.ProposalInsuredPerson;

import org.ace.insurance.product.Product;
import org.ace.insurance.system.common.keyfactor.KeyFactor;


public class InsuredPersonInfoDTO {
	private String productId;
	private Product product;
	private String planId;
	private Plans plans;
	private String planType;
	private double premium;
	private double sumInsured;
	private Name name;
	private String insuredName;
	private int age;
	private Gender gender;	
	private IdType idType;	
	private String fullIdNo;
	private String passportNo;
	private long dateOfBirth;
	private ResidentAddress residentAddress;;
	private String insuResidentAddress;
	private String fatherName;
	private String cdcNo;
	private String position;
	private String oceanlinerName;
	private String vesselName;	
	private List<InsuredPersonKeyFactorValue> keyFactorValueList;
	private List<BeneficiariesInfoDTO> beneficiariesInfoDTOList;
	private List<PolicyInsuredPersonKeyFactorValue> policyKeyFactorValueList;
	private int version;

	public InsuredPersonInfoDTO(ProposalInsuredPerson proposalInsuredPerson) {
		this.productId = proposalInsuredPerson.getProduct().getId();
		this.planId = proposalInsuredPerson.getPlans().getId();
		this.planType = proposalInsuredPerson.getPlans().getPlanType();
		this.premium = proposalInsuredPerson.getTermPremium();
		this.sumInsured = proposalInsuredPerson.getApprovedSumInsured();
		this.insuredName = proposalInsuredPerson.getName().getFirstName();		
		this.idType = proposalInsuredPerson.getIdType();
		this.fullIdNo = proposalInsuredPerson.getIdNo();
		this.passportNo = proposalInsuredPerson.getPassportNo();
		this.dateOfBirth = proposalInsuredPerson.getDateOfBirth().getTime();
		this.age = proposalInsuredPerson.getAge();
		this.gender = proposalInsuredPerson.getGender();
		this.insuResidentAddress = proposalInsuredPerson.getResidentAddress().getResidentAddress();
		this.fatherName  = proposalInsuredPerson.getFatherName();			
		this.oceanlinerName = proposalInsuredPerson.getOceanlinerName();
		this.vesselName = proposalInsuredPerson.getVesselName();
		this.position = proposalInsuredPerson.getPosition();		
		this.cdcNo = proposalInsuredPerson.getCdcNo();
		for (InsuredPersonBeneficiaries beneficiary : proposalInsuredPerson.getInsuredPersonBeneficiariesList()) {
			addBeneficiariesInfoDTO(new BeneficiariesInfoDTO(beneficiary));
		}

	}

	public InsuredPersonInfoDTO(PolicyInsuredPerson pi) {
		this.age = pi.getAge();
		this.premium = pi.getPremium();
		this.fatherName = pi.getFatherName();
		this.dateOfBirth = pi.getDateOfBirth().getTime();
		this.gender = pi.getGender();
		this.idType = pi.getIdType();
		this.fullIdNo = pi.getIdNo();
		this.insuResidentAddress = pi.getResidentAddress().getResidentAddress();
		this.name = pi.getName();
		this.productId = pi.getProduct().getId();
		this.passportNo = pi.getPassportNo();
		this.oceanlinerName=pi.getOceanlinerName();
		this.vesselName=pi.getVesselName();
		this.cdcNo = pi.getCdcNo();
		this.position = pi.getPosition();
		for (PolicyInsuredPersonKeyFactorValue kfv : pi.getPolicyInsuredPersonkeyFactorValueList()) {
			addPolicyInsuredPersonKeyFactorValue(kfv);
		}
		for (PolicyInsuredPersonBeneficiaries beneficiary : pi.getPolicyInsuredPersonBeneficiariesList()) {
			addBeneficiariesInfoDTO(new BeneficiariesInfoDTO(beneficiary));
		}

	}





	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}


	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}




	public List<InsuredPersonKeyFactorValue> getKeyFactorValueList(ProposalInsuredPerson proposalInsuredPerson) {
		if (proposalInsuredPerson.getKeyFactorValueList() == null || keyFactorValueList == null) {
			return new ArrayList<InsuredPersonKeyFactorValue>();
		} else {
			for (InsuredPersonKeyFactorValue inskf : keyFactorValueList) {
				inskf.setProposalInsuredPerson(proposalInsuredPerson);
			}
			return keyFactorValueList;
		}

	}


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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




	public Name getName() {
		if (name == null) {
			name = new Name();
		}
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}



	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}



	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getApprovedSumInsured() {
		return sumInsured;
	}

	public void setApprovedSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}



	public List<InsuredPersonKeyFactorValue> getKeyFactorValueList() {
		if (keyFactorValueList == null) {
			keyFactorValueList = new ArrayList<InsuredPersonKeyFactorValue>();
		}
		return keyFactorValueList;
	}

	public void setKeyFactorValueList(List<InsuredPersonKeyFactorValue> keyFactorValueList) {
		this.keyFactorValueList = keyFactorValueList;
	}

	public void addInsuredPersonKeyFactorValue(InsuredPersonKeyFactorValue kfv) {
		getKeyFactorValueList().add(kfv);
	}

	public List<BeneficiariesInfoDTO> getBeneficiariesInfoDTOList() {
		if (beneficiariesInfoDTOList == null) {
			beneficiariesInfoDTOList = new ArrayList<BeneficiariesInfoDTO>();
		}
		return beneficiariesInfoDTOList;
	}

	public void setBeneficiariesInfoDTOList(List<BeneficiariesInfoDTO> beneficiariesInfoDTO1List) {
		this.beneficiariesInfoDTOList = beneficiariesInfoDTO1List;
	}

	public void addBeneficiariesInfoDTO(BeneficiariesInfoDTO dto) {
		getBeneficiariesInfoDTOList().add(dto);
	}


	public List<PolicyInsuredPersonKeyFactorValue> getPolicyKeyFactorValueList() {
		if (policyKeyFactorValueList == null) {
			policyKeyFactorValueList = new ArrayList<PolicyInsuredPersonKeyFactorValue>();
		}
		return policyKeyFactorValueList;
	}

	public void setPolicyKeyFactorValueList(List<PolicyInsuredPersonKeyFactorValue> policyKeyFactorValueList) {
		this.policyKeyFactorValueList = policyKeyFactorValueList;
	}

	public void addPolicyInsuredPersonKeyFactorValue(PolicyInsuredPersonKeyFactorValue kfv) {
		getPolicyKeyFactorValueList().add(kfv);
	}

	public String getFullIdNo() {
		return fullIdNo;
	}

	public void setFullIdNo(String fullIdNo) {
		this.fullIdNo = fullIdNo;
	}
	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}



	public String getCdcNo() {
		return cdcNo;
	}

	public void setCdcNo(String cdcNo) {
		this.cdcNo = cdcNo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}




	public String getOceanlinerName() {
		return oceanlinerName;
	}

	public void setOceanlinerName(String oceanlinerName) {
		this.oceanlinerName = oceanlinerName;
	}

	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}

	
	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredPersonName(String insuredName) {
		this.insuredName = insuredName;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		loadKeyFactor(product);
	}
	
	private void loadKeyFactor(Product product) {
		keyFactorValueList = new ArrayList<InsuredPersonKeyFactorValue>();		
		if (product.getKeyFactorList() != null) {
			for (KeyFactor kf : product.getKeyFactorList()) {
				InsuredPersonKeyFactorValue insKf = new InsuredPersonKeyFactorValue(kf);
				insKf.setKeyFactor(kf);
				keyFactorValueList.add(insKf);
			}
		}
	}

	
	public String getInsuResidentAddress() {
		return insuResidentAddress;
	}

	public void setInsuResidentAddress(String insuResidentAddress) {
		this.insuResidentAddress = insuResidentAddress;
	}

	public ResidentAddress getResidentAddress() {
		return residentAddress;
	}

	public void setResidentAddress(ResidentAddress residentAddress) {
		this.residentAddress = residentAddress;
	}
	
	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Plans getPlans() {
		return plans;
	}

	public void setPlans(Plans plans) {
		this.plans = plans;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}
	
	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}
	
	
	
	

}
