package org.ace.insurance.life.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.ace.insurance.common.AbstractMynNumConvertor;
import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.Name;
import org.ace.insurance.common.ResidentAddress;
import org.ace.insurance.life.enums.IdConditionTypeMM;
import org.ace.insurance.system.common.relationship.RelationShip;

public class BeneficiariesInfoDTO {
	private boolean existEntity;
	private int age;
	private float percentage;
	private String tempId;
	private String beneficiaryNo;
	private String phone;

	private RelationShip relationship;
	private String initialId;
	private Gender gender;
	private IdType idType;
	private ResidentAddress residentAddress;

	private Date dateOfBirth;
	private Name name;
	private String idNo;
	private String idNoMM;
	private String provinceCode;
	private String provinceCodeMM;
	private String townshipCode;
	private String townshipCodeENG;
	private String townshipCodeMM;
	private String idConditionType;
	private String idConditionTypeMM;
	private String fullIdNo;
	private String fullIdNoMM;
	private int version;
	private boolean valid;
	private int insuredId;
	private String email;


	public BeneficiariesInfoDTO() {
		tempId = System.nanoTime() + "";
	}

	public BeneficiariesInfoDTO(org.ace.insurance.life.dao.entities.InsuredPersonBeneficiaries beneficiary) {
		if (beneficiary.getId() == null) {
			this.tempId = System.nanoTime() + "";
		} else {
			existEntity = true;
			this.tempId = beneficiary.getId();
			this.version = beneficiary.getVersion();
		}
		this.dateOfBirth = beneficiary.getDateOfBirth();
		this.age = beneficiary.getAge();
		this.percentage = beneficiary.getPercentage();
		this.beneficiaryNo = beneficiary.getBeneficiaryNo();
		this.initialId = beneficiary.getInitialId();
		this.idType = beneficiary.getIdType();
		this.fullIdNo = beneficiary.getIdNo();
		this.fullIdNoMM = beneficiary.getIdNoMM();
		loadFullIdNo();
		loadFullIdNoMM();
		if(this.fullIdNoMM != null) {
		this.townshipCode = this.townshipCodeENG+"/"+this.townshipCodeMM;
		}else {
			this.townshipCode = this.townshipCodeENG;
		}
		this.gender = beneficiary.getGender();
		this.residentAddress = beneficiary.getResidentAddress();
		this.name = beneficiary.getName();
		this.relationship = beneficiary.getRelationship();
		this.phone = beneficiary.getPhone();
		this.email = beneficiary.getEmail();
	}

	public BeneficiariesInfoDTO(org.ace.insurance.life.dao.entities.PolicyInsuredPersonBeneficiaries policyInsuredPersonBeneficiaries) {
		if (policyInsuredPersonBeneficiaries.getId() == null) {
			this.tempId = System.nanoTime() + "";
		} else {
			this.tempId = policyInsuredPersonBeneficiaries.getId();
			this.version = policyInsuredPersonBeneficiaries.getVersion();
		}
		this.age = policyInsuredPersonBeneficiaries.getAge();
		this.percentage = policyInsuredPersonBeneficiaries.getPercentage();
		this.beneficiaryNo = policyInsuredPersonBeneficiaries.getBeneficiaryNo();
		this.initialId = policyInsuredPersonBeneficiaries.getInitialId();
		this.gender = policyInsuredPersonBeneficiaries.getGender();
		this.idType = policyInsuredPersonBeneficiaries.getIdType();
		this.fullIdNo = policyInsuredPersonBeneficiaries.getIdNo();
		this.fullIdNoMM = policyInsuredPersonBeneficiaries.getIdNoMM();
		this.dateOfBirth = policyInsuredPersonBeneficiaries.getDateOfBirth();
		loadFullIdNo();
		if(fullIdNoMM != null) {
			loadFullIdNoMM();
			this.townshipCode = this.townshipCodeENG + "/" +this.townshipCodeMM;
		}else {
			this.townshipCode = this.townshipCodeENG;			
		}
		
		this.idNo = policyInsuredPersonBeneficiaries.getIdNo();
		this.residentAddress = policyInsuredPersonBeneficiaries.getResidentAddress();
		this.name = policyInsuredPersonBeneficiaries.getName();
		this.relationship = policyInsuredPersonBeneficiaries.getRelationship();
		this.phone = policyInsuredPersonBeneficiaries.getPhone();
		this.email = policyInsuredPersonBeneficiaries.getEmail();
	}

	public BeneficiariesInfoDTO(BeneficiariesInfoDTO beneficiary) {
		this.tempId = beneficiary.getTempId();
		this.existEntity = beneficiary.isExistEntity();
		this.version = beneficiary.getVersion();
		this.age = beneficiary.getAge();
		this.dateOfBirth = beneficiary.getDateOfBirth();
		this.percentage = beneficiary.getPercentage();
		this.beneficiaryNo = beneficiary.getBeneficiaryNo();
		this.initialId = beneficiary.getInitialId();
		this.fullIdNo = beneficiary.getFullIdNo();
		this.gender = beneficiary.getGender();
		this.idType = beneficiary.getIdType();
		this.residentAddress = beneficiary.getResidentAddress();
		this.name = beneficiary.getName();
		this.relationship = beneficiary.getRelationship();
		this.idConditionType = beneficiary.getIdConditionType();
		this.provinceCode = beneficiary.getProvinceCode();
		this.townshipCode = beneficiary.getTownshipCode();
		this.idNo = beneficiary.getIdNo();
		this.phone = beneficiary.getPhone();
		this.insuredId = beneficiary.getInsuredId();
		this.email = beneficiary.getEmail();
		this.valid = beneficiary.isValid();
	}

	public String getBeneficiaryNo() {
		return beneficiaryNo;
	}

	public void setBeneficiaryNo(String beneficiaryNo) {
		this.beneficiaryNo = beneficiaryNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
	public RelationShip getRelationship() {
		return relationship;
	}

	public void setRelationship(RelationShip relationship) {
		this.relationship = relationship;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public boolean isValidBeneficiaries() {
		/*
		 * if(customer == null) { return false; }
		 */
		if (percentage <= 0) {
			return false;
		}
		if (age < 0) {
			return false;
		}
		return true;
	}

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullName() {
		String result = "";
		if (name != null) {
			if (initialId != null && !initialId.isEmpty()) {
				result = initialId;
			}
			if (name.getFirstName() != null && !name.getFirstName().isEmpty()) {
				result = result + " " + name.getFirstName();
			}
			if (name.getMiddleName() != null && !name.getMiddleName().isEmpty()) {
				result = result + " " + name.getMiddleName();
			}
			if (name.getLastName() != null && !name.getLastName().isEmpty()) {
				result = result + " " + name.getLastName();
			}
		}
		return result;
	}

	public void loadFullIdNo() {
		if (null != idType && idType.equals(IdType.NRCNO) && fullIdNo != null && !fullIdNo.isEmpty()) {
			StringTokenizer token = new StringTokenizer(fullIdNo, "/()");
			provinceCode = token.nextToken();
			townshipCodeENG = token.nextToken();
			idConditionType = token.nextToken();
			idNo = token.nextToken();
			fullIdNo = provinceCode.equals("null") ? "" : fullIdNo;
		} else if ((null != idType && idType.equals(IdType.FRCNO)) || (null != idType && idType.equals(IdType.PASSPORTNO))) {
			idNo = fullIdNo == null ? "" : fullIdNo;
		}
	}
	
	public void loadFullIdNoForExcel() {
		if (null != idType && idType.equals(IdType.NRCNO) && fullIdNo != null && !fullIdNo.isEmpty()) {
			StringTokenizer token = new StringTokenizer(fullIdNo, "/()");
			provinceCode = token.nextToken();
			townshipCodeENG = token.nextToken();
			townshipCodeMM = token.nextToken();
			idConditionType = token.nextToken();
			idNo = token.nextToken();
			fullIdNo = provinceCode.equals("null") ? "" : provinceCode + "/" + townshipCodeENG + "(" + idConditionType + ")" + idNo;
		} else if ((null != idType && idType.equals(IdType.FRCNO)) || (null != idType && idType.equals(IdType.PASSPORTNO))) {
			idNo = fullIdNo == null ? "" : fullIdNo;
		}
	}
	
	public String setBenefFullIdNo() {
		if (idType.equals(IdType.NRCNO)) {
			fullIdNo = provinceCode + "/" + townshipCodeENG + "(" + idConditionType + ")" + idNo;
		} else if (idType.equals(IdType.FRCNO) || idType.equals(IdType.PASSPORTNO)) {
			fullIdNo = idNo;
		}else {
			fullIdNo = null;
		}
		return fullIdNo;
	}
	public void loadFullIdNoMM() {
		if(fullIdNoMM != null && !fullIdNoMM.isEmpty()) {
			StringTokenizer token = new StringTokenizer(fullIdNoMM, "/()");
			provinceCodeMM = token.nextToken();
			townshipCodeMM = token.nextToken();
			idConditionTypeMM = token.nextToken();
			idNoMM = token.nextToken();
			fullIdNoMM = provinceCodeMM.equals("null") ? "" : fullIdNoMM;			
		}
	}
	
	public String setBenefFullIdNoMM() {
		if(IdType.NRCNO.equals(idType)) {
			idNoMM = AbstractMynNumConvertor.getAmountWithMyanmar(idNo);
			provinceCodeMM = AbstractMynNumConvertor.getAmountWithMyanmar(provinceCode);
			idConditionTypeMM = IdConditionTypeMM.valueOf(idConditionType).getLabel();
			fullIdNoMM = provinceCodeMM+"/"+townshipCodeMM+"("+idConditionTypeMM+")"+idNoMM;			
		} else {
			fullIdNoMM = null;
		}
		return fullIdNoMM;
		
		
	}
	
	public String setBenefIdNo() {
		if (idType.equals(IdType.NRCNO)) {
			idNo = provinceCode + "/" + townshipCodeENG + "(" + idConditionType + ")" + idNo;
		}
		return idNo;
	}
	
	public String setBenefIdNoMM() {
		return idNoMM = provinceCode + "/" + townshipCodeENG + "(" + idConditionType + ")" + idNo;		
	}

	
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isExistEntity() {
		return existEntity;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getTownshipCode() {
		return townshipCode;
	}

	public void setTownshipCode(String townshipCode) {
		this.townshipCode = townshipCode;
	}

	public String getIdConditionType() {
		return idConditionType;
	}

	public void setIdConditionType(String idConditionType) {
		this.idConditionType = idConditionType;
	}

	public String getFullIdNo() {
		return fullIdNo;
	}

	public void setFullIdNo(String fullIdNo) {
		this.fullIdNo = fullIdNo;
	}

	public void setExistEntity(boolean existEntity) {
		this.existEntity = existEntity;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public int getInsuredId() {
		return insuredId;
	}

	public void setInsuredId(int insuredId) {
		this.insuredId = insuredId;
	}
	

	public String getFullIdNoMM() {
		return fullIdNoMM;
		
	}

	public void setFullIdNoMM(String fullIdNoMM) {
		this.fullIdNoMM = fullIdNoMM;
		
	}

	public String getIdNoMM() {
		return idNoMM;
		
	}

	public void setIdNoMM(String idNoMM) {
		this.idNoMM = idNoMM;
		
	}

	public String getProvinceCodeMM() {
		return provinceCodeMM;
		
	}

	public void setProvinceCodeMM(String provinceCodeMM) {
		this.provinceCodeMM = provinceCodeMM;
		
	}

	public String getTownshipCodeMM() {
		return townshipCodeMM;
		
	}

	public void setTownshipCodeMM(String townshipCodeMM) {
		this.townshipCodeMM = townshipCodeMM;
		
	}
	public String getTownshipCodeENG() {
		return townshipCodeENG;
	}

	public void setTownshipCodeENG(String townshipCodeENG) {
		this.townshipCodeENG = townshipCodeENG;
	}

	public String getIdConditionTypeMM() {
		return idConditionTypeMM;
		
	}

	public void setIdConditionTypeMM(String idConditionTypeMM) {
		this.idConditionTypeMM = idConditionTypeMM;
		
	}
	
	public  int getAgeForNextYear() {
		if (dateOfBirth != null) {
			Calendar cal_1 = Calendar.getInstance();
			int currentYear = cal_1.get(Calendar.YEAR);
			Calendar cal_2 = Calendar.getInstance();
			cal_2.setTime(dateOfBirth);
			cal_2.set(Calendar.YEAR, currentYear);
			if (new Date().after(cal_2.getTime())) {
				Calendar cal_3 = Calendar.getInstance();
				cal_3.setTime(dateOfBirth);
				int year_1 = cal_3.get(Calendar.YEAR);
				int year_2 = cal_1.get(Calendar.YEAR) + 1;
				return year_2 - year_1;
			} else {
				Calendar cal_3 = Calendar.getInstance();
				cal_3.setTime(dateOfBirth);
				int year_1 = cal_3.get(Calendar.YEAR);
				int year_2 = cal_1.get(Calendar.YEAR);
				return year_2 - year_1;
			}
		} else
			return 0;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	


}
