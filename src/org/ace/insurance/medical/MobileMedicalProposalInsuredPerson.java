package org.ace.insurance.medical;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.insurance.medical.surveyAnswer.SurveyQuestionAnswer;
import org.ace.insurance.medical.surveyAnswer.SurveyQuestionAnswerDTO;
import org.ace.java.component.idgen.service.IDInterceptor;
import org.ace.ws.model.mobileMedicalproposal.MedicalAddOnDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalBeneficiariesDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalInsuredPersonDTO;

@Entity
@Table(name = TableName.MOBILE_MEDICALPROPOSALINSUREDPERSON)
@TableGenerator(name = "MEDICALPROPOSALINSUREDPERSON_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MEDICALPROPOSALINSUREDPERSON_GEN", allocationSize = 10)
@EntityListeners(IDInterceptor.class)
public class MobileMedicalProposalInsuredPerson {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "MEDICALPROPOSALINSUREDPERSON_GEN")
	private String id;
	private int age;
	private int unit;
	private double sumInsured;
	private double basicPremium;
	private double addOnPremium;
	@Column(name = "INSPERSONCODENO")
	private String insPersonCodeNo;

	@Column(name = "INPERSONGROUPCODENO")
	private String inPersonGroupCodeNo;

	private String relationshipId;
	private String productId;
	private String initialId;
	private String firstName;
	private String lastName;
	private String middleName;
	private Date dateOfBirth;
	private String fatherName;
	private String idNo;
	private String occupationId;
	private String townshipId;
	private String address;
	private String phoneNo;
	private double height;
	private double weight;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "MEDIPROPOSALINSUREDPERSONID", referencedColumnName = "ID")
	private List<MobileMedicalProposalInsuredPersonAddOn> insuredPersonAddOnList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	private List<MobileMedicalProposalInsuredPersonBeneficiaries> insuredPersonBeneficiariesList;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	private List<SurveyQuestionAnswer> surveyQuestionAnswerList;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public MobileMedicalProposalInsuredPerson() {
	}

	public MobileMedicalProposalInsuredPerson(MedicalProposalInsuredPersonDTO mip001) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = mip001.getId();
		this.age = mip001.getAge();
		this.unit = mip001.getUnit();
		this.sumInsured = mip001.getSumInsured();
		this.basicPremium = mip001.getBasicPremium();
		this.addOnPremium = mip001.getAddOnPremium();
		this.insPersonCodeNo = mip001.getInsPersonCodeNo();

		this.inPersonGroupCodeNo = mip001.getInPersonGroupCodeNo();

		this.relationshipId = mip001.getRelationshipId();
		this.productId = mip001.getProductId();
		this.initialId = mip001.getInitialId();
		this.firstName = mip001.getFirstName();
		this.lastName = mip001.getLastName();
		this.middleName = mip001.getMiddleName();
		try {
			this.dateOfBirth = mip001.getDateOfBirth()!= null ? sdf.parse(mip001.getDateOfBirth()) : null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fatherName = mip001.getFatherName();
		this.idNo = mip001.getIdNo();
		this.occupationId = mip001.getOccupationId();
		this.townshipId = mip001.getTownshipId();
		this.address = mip001.getAddress();
		this.phoneNo = mip001.getPhoneNo();
		this.height = mip001.getHeight();
		this.weight = mip001.getWeight();
		this.idType = mip001.getIdType();
		this.gender = mip001.getGender();

		if (mip001.getInsuredPersonBeneficiariesList() != null) {
			insuredPersonBeneficiariesList = new ArrayList<MobileMedicalProposalInsuredPersonBeneficiaries>();
			for (MedicalBeneficiariesDTO mb001 : mip001.getInsuredPersonBeneficiariesList()) {
				insuredPersonBeneficiariesList.add(new MobileMedicalProposalInsuredPersonBeneficiaries(mb001));
			}
		}

		if (mip001.getInsuredPersonAddOnList() != null) {
			insuredPersonAddOnList = new ArrayList<>();
			for (MedicalAddOnDTO mipa001 : mip001.getInsuredPersonAddOnList()) {
				insuredPersonAddOnList.add(new MobileMedicalProposalInsuredPersonAddOn(mipa001));
			}
		}

		if (mip001.getSurveyQuestionAnswerList() != null) {
			surveyQuestionAnswerList = new ArrayList<>();
			for (SurveyQuestionAnswerDTO mipa001 : mip001.getSurveyQuestionAnswerList()) {
				surveyQuestionAnswerList.add(new SurveyQuestionAnswer(mipa001));
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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

	public List<MobileMedicalProposalInsuredPersonAddOn> getInsuredPersonAddOnList() {
		return insuredPersonAddOnList;
	}

	public void setInsuredPersonAddOnList(List<MobileMedicalProposalInsuredPersonAddOn> insuredPersonAddOnList) {
		this.insuredPersonAddOnList = insuredPersonAddOnList;
	}

	public List<MobileMedicalProposalInsuredPersonBeneficiaries> getInsuredPersonBeneficiariesList() {
		return insuredPersonBeneficiariesList;
	}

	public void setInsuredPersonBeneficiariesList(List<MobileMedicalProposalInsuredPersonBeneficiaries> insuredPersonBeneficiariesList) {
		this.insuredPersonBeneficiariesList = insuredPersonBeneficiariesList;
	}

	public List<SurveyQuestionAnswer> getSurveyQuestionAnswerList() {
		return surveyQuestionAnswerList;
	}

	public void setSurveyQuestionAnswerList(List<SurveyQuestionAnswer> surveyQuestionAnswerList) {
		this.surveyQuestionAnswerList = surveyQuestionAnswerList;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getFullName() {
		String result = "";
		if (initialId != null && !initialId.isEmpty()) {
			result += initialId + " ";
		}
		if (firstName != null && !firstName.isEmpty()) {
			result += firstName + " ";
		}
		if (middleName != null && !middleName.isEmpty()) {
			result += middleName + " ";
		}
		if (lastName != null && !lastName.isEmpty()) {
			result += lastName + "";
		}

		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(addOnPremium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		temp = Double.doubleToLongBits(basicPremium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((fatherName == null) ? 0 : fatherName.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idNo == null) ? 0 : idNo.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		result = prime * result + ((inPersonGroupCodeNo == null) ? 0 : inPersonGroupCodeNo.hashCode());
		result = prime * result + ((initialId == null) ? 0 : initialId.hashCode());
		result = prime * result + ((insPersonCodeNo == null) ? 0 : insPersonCodeNo.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((occupationId == null) ? 0 : occupationId.hashCode());
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((relationshipId == null) ? 0 : relationshipId.hashCode());
		temp = Double.doubleToLongBits(sumInsured);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((townshipId == null) ? 0 : townshipId.hashCode());
		result = prime * result + unit;
		result = prime * result + version;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		MobileMedicalProposalInsuredPerson other = (MobileMedicalProposalInsuredPerson) obj;
		if (Double.doubleToLongBits(addOnPremium) != Double.doubleToLongBits(other.addOnPremium))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (Double.doubleToLongBits(basicPremium) != Double.doubleToLongBits(other.basicPremium))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (fatherName == null) {
			if (other.fatherName != null)
				return false;
		} else if (!fatherName.equals(other.fatherName))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idNo == null) {
			if (other.idNo != null)
				return false;
		} else if (!idNo.equals(other.idNo))
			return false;
		if (idType != other.idType)
			return false;
		if (inPersonGroupCodeNo == null) {
			if (other.inPersonGroupCodeNo != null)
				return false;
		} else if (!inPersonGroupCodeNo.equals(other.inPersonGroupCodeNo))
			return false;
		if (initialId == null) {
			if (other.initialId != null)
				return false;
		} else if (!initialId.equals(other.initialId))
			return false;
		if (insPersonCodeNo == null) {
			if (other.insPersonCodeNo != null)
				return false;
		} else if (!insPersonCodeNo.equals(other.insPersonCodeNo))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (occupationId == null) {
			if (other.occupationId != null)
				return false;
		} else if (!occupationId.equals(other.occupationId))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (relationshipId == null) {
			if (other.relationshipId != null)
				return false;
		} else if (!relationshipId.equals(other.relationshipId))
			return false;
		if (Double.doubleToLongBits(sumInsured) != Double.doubleToLongBits(other.sumInsured))
			return false;
		if (townshipId == null) {
			if (other.townshipId != null)
				return false;
		} else if (!townshipId.equals(other.townshipId))
			return false;
		if (unit != other.unit)
			return false;
		if (version != other.version)
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

}
