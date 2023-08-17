package org.ace.insurance.personalAccident;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.TableName;
import org.ace.java.component.FormatID;
import org.ace.ws.model.mobilePersonalAccidentproposal.MBP001;

@Entity
@Table(name = TableName.INSUREDPERSON_BENEFICIARIES)
@TableGenerator(name = "INSUREDPERSON_BENEFICIARIES_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "INSUREDPERSON_BENEFICIARIES_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "GInsuredPersonBeneficiaries.findAll", query = "SELECT p FROM GInsuredPersonBeneficiaries p "),
		@NamedQuery(name = "GInsuredPersonBeneficiaries.findById", query = "SELECT p FROM GInsuredPersonBeneficiaries p WHERE p.id = :id") })
@Access(value = AccessType.FIELD)
public class GInsuredPersonBeneficiaries {
	private float percentage;
	@Transient
	private String id;
	private String prefix;
	private String initialId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String idNo;
	private String address;
	private String townshipId;
	private Date dateOfBirth;
	private String relationshipId;
	private String nrcFrontName;
	private String nrcBackName;
	private String nrcFrontfilePath;
	private String nrcBackfilePath;
	private String phoneNumber;

	private byte[] nrcFrontImage;
	private byte[] nrcBackImage;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	// private MobilePersonalAccidentInsuredPerson proposalInsuredPerson;

	@Version
	private int version;

	public GInsuredPersonBeneficiaries() {
	}

	public GInsuredPersonBeneficiaries(MBP001 dto) {
		this.id = dto.getId();
		this.initialId = dto.getInitialId();
		this.firstName = dto.getFirstName();
		this.lastName = dto.getLastName();
		this.middleName = dto.getMiddleName();
		this.idNo = dto.getIdNo();
		this.address = dto.getAddress();
		this.gender = dto.getGender();
		this.idType = dto.getIdType();
		this.phoneNumber = dto.getPhoneNumber();
		this.dateOfBirth = new Date(dto.getDateOfBirth());
		this.townshipId = dto.getTownshipId();
		this.relationshipId = dto.getRelationshipId();
		this.percentage = dto.getPercentage();
		this.nrcBackName = dto.getNrcBackName();
		this.nrcFrontName = dto.getNrcFrontName();
		this.nrcBackfilePath = dto.getNrcBackfilePath();
		this.nrcFrontfilePath = dto.getNrcFrontfilePath();
		this.nrcBackImage = dto.getNrcBackImage();
		this.nrcFrontImage = dto.getNrcFrontImage();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "INSUREDPERSON_BENEFICIARIES_GEN")
	@Access(value = AccessType.PROPERTY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = FormatID.formatId(id, getPrefix(), 10);
		}
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public byte[] getNrcFrontImage() {
		return nrcFrontImage;
	}

	public void setNrcFrontImage(byte[] nrcFrontImage) {
		this.nrcFrontImage = nrcFrontImage;
	}

	public byte[] getNrcBackImage() {
		return nrcBackImage;
	}

	public void setNrcBackImage(byte[] nrcBackImage) {
		this.nrcBackImage = nrcBackImage;
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

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(String townshipId) {
		this.townshipId = townshipId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNameF() {
		return nrcFrontName;
	}

	public void setNameF(String nameF) {
		this.nrcFrontName = nameF;
	}

	public String getNameB() {
		return nrcBackName;
	}

	public void setNameB(String nameB) {
		this.nrcBackName = nameB;
	}

	public String getFilePathF() {
		return nrcFrontfilePath;
	}

	public void setFilePathF(String filePathF) {
		this.nrcFrontfilePath = filePathF;
	}

	public String getFilePathB() {
		return nrcBackfilePath;
	}

	public void setFilePathB(String filePathB) {
		this.nrcBackfilePath = filePathB;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idNo == null) ? 0 : idNo.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		result = prime * result + ((initialId == null) ? 0 : initialId.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + Float.floatToIntBits(percentage);
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
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
		GInsuredPersonBeneficiaries other = (GInsuredPersonBeneficiaries) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
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
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
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
