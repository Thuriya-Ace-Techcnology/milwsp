package org.ace.insurance.medical;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;
import org.ace.java.component.idgen.service.IDInterceptor;
import org.ace.ws.model.mobileMedicalproposal.MedicalBeneficiariesDTO;

@Entity
@Table(name = TableName.MOBILE_MEDICALPROPOSALINSUREDPERSONBENEFICIARIES)
@TableGenerator(name = "MPINSUREDPERSONBENEFICIARIES_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MPINSUREDPERSONBENEFICIARIES_GEN", allocationSize = 10)
@EntityListeners(IDInterceptor.class)
public class MobileMedicalProposalInsuredPersonBeneficiaries {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MPINSUREDPERSONBENEFICIARIES_GEN")
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

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	private String idNo;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public MobileMedicalProposalInsuredPersonBeneficiaries() {
	}

	public MobileMedicalProposalInsuredPersonBeneficiaries(MedicalBeneficiariesDTO mb001) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.id = mb001.getId();
		this.beneficiaryNo = mb001.getBeneficiaryNo();
		this.initialId = mb001.getInitialId();
		this.firstName = mb001.getFirstName();
		this.lastName = mb001.getLastName();
		this.middleName = mb001.getMiddleName();
		this.fatherName = mb001.getFatherName();
		this.townshipId = mb001.getTownshipId();
		this.address = mb001.getAddress();
		this.phoneNo = mb001.getPhoneNo();
		this.percentage = mb001.getPercentage();
		this.relationshipId = mb001.getRelationshipId();
		try {
			this.dateOfBirth = mb001.getDateOfBirth()!= null ? sdf.parse(mb001.getDateOfBirth()) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.gender = mb001.getGender();
		this.idType = mb001.getIdType();
		this.idNo = mb001.getIdNo();
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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
			result = initialId.trim();
		}
		if (firstName != null && !firstName.isEmpty()) {
			result = result + " " + firstName.trim();
		}
		if (middleName != null && !middleName.isEmpty()) {
			result = result + " " + middleName.trim();
		}
		if (lastName != null && !lastName.isEmpty()) {
			result = result + " " + lastName;
		}

		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((beneficiaryNo == null) ? 0 : beneficiaryNo.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((fatherName == null) ? 0 : fatherName.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idNo == null) ? 0 : idNo.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		result = prime * result + ((initialId == null) ? 0 : initialId.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + Float.floatToIntBits(percentage);
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((relationshipId == null) ? 0 : relationshipId.hashCode());
		result = prime * result + ((townshipId == null) ? 0 : townshipId.hashCode());
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
		MobileMedicalProposalInsuredPersonBeneficiaries other = (MobileMedicalProposalInsuredPersonBeneficiaries) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (beneficiaryNo == null) {
			if (other.beneficiaryNo != null)
				return false;
		} else if (!beneficiaryNo.equals(other.beneficiaryNo))
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
		if (initialId == null) {
			if (other.initialId != null)
				return false;
		} else if (!initialId.equals(other.initialId))
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
		if (Float.floatToIntBits(percentage) != Float.floatToIntBits(other.percentage))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
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
		if (townshipId == null) {
			if (other.townshipId != null)
				return false;
		} else if (!townshipId.equals(other.townshipId))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
