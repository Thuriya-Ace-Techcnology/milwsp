package org.ace.insurance.personalAccident;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.IdType;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.Utils;
import org.ace.insurance.life.dao.entities.InsuredPersonBeneficiaries;
import org.ace.java.component.FormatID;
import org.ace.ws.model.mobilePersonalAccidentproposal.MBP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.MIPA001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAIP001;

@Entity
@Table(name = TableName.MOBILE_PERSONALACCIDENT_INSURED_PERSON)

@TableGenerator(name = "MOBILE_PERSONALACCIDENT_INSURED_PERSON_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MOBILE_PERSONALACCIDENT_INSURED_PERSON_GEN", allocationSize = 10)
@NamedQueries(value = { @NamedQuery(name = "MobilePersonalAccidentInsuredPerson.findAll", query = "SELECT m FROM MobilePersonalAccidentInsuredPerson m ORDER BY m.firstName ASC"),
		@NamedQuery(name = "MobilePersonalAccidentInsuredPerson.findById", query = "SELECT m FROM MobilePersonalAccidentInsuredPerson m WHERE m.id = :id") })
@Access(value = AccessType.FIELD)
public class MobilePersonalAccidentInsuredPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	@Transient
	private String prefix;
	private String initialId;
	private String firstName;
	private String lastName;
	private String middleName;
	private String fatherName;
	private String idNo;
	private String address;
	private String productId;
	private String occupationId;
	private String townshipId;
	private Date dateOfBirth;
	private int age;
	private double sumInsured;
	private double premium;
	private String nrcFrontName;
	private String nrcBackName;
	private String nrcFrontfilePath;
	private String nrcBackfilePath;
	private byte[] nrcFrontImage;
	private byte[] nrcBackImage;
	
	private String phoneNumber;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "MOBILEPROPOSALINSUREDPERSONID", referencedColumnName = "ID")
	private List<GInsuredPersonAddon> insuredPersonAddOnList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	private List<GInsuredPersonBeneficiaries> insuredPersonBeneficiariesList;

	@Version
	private int version;

	public MobilePersonalAccidentInsuredPerson() {

	}

	public MobilePersonalAccidentInsuredPerson(PAIP001 insuredPerson) {
		this.id = insuredPerson.getId();
		this.initialId = insuredPerson.getInitialId();
		this.firstName = insuredPerson.getFirstName();
		this.lastName = insuredPerson.getLastName();
		this.middleName = insuredPerson.getMiddleName();
		this.fatherName = insuredPerson.getFatherName();
		this.idNo = insuredPerson.getIdNo();
		this.dateOfBirth = new Date(insuredPerson.getDateOfBirth());
		this.age = Utils.getAgeForNextYear(new Date(insuredPerson.getDateOfBirth()));
		this.sumInsured = insuredPerson.getSumInsured();
		this.premium = insuredPerson.getPremium();
		this.gender = insuredPerson.getGender();
		this.idType = insuredPerson.getIdType();
		this.address = insuredPerson.getAddress();
		this.townshipId = insuredPerson.getTownshipId();
		this.productId = insuredPerson.getProductId();
		this.phoneNumber = insuredPerson.getPhoneNumber();
		this.occupationId = insuredPerson.getOccupationId();
		this.nrcBackName = insuredPerson.getNrcBackName();
		this.nrcFrontName = insuredPerson.getNrcFrontName();
		this.nrcBackfilePath = insuredPerson.getNrcBackfilePath();
		this.nrcFrontfilePath = insuredPerson.getNrcFrontfilePath();
		this.nrcFrontImage = insuredPerson.getNrcFrontImage();
		this.nrcBackImage = insuredPerson.getNrcBackImage();
		List<GInsuredPersonBeneficiaries> insuredBeneficiaryList = new ArrayList<GInsuredPersonBeneficiaries>();
		if (insuredPerson.getInsuredBeneficiaryList() != null) {
			for (MBP001 mbp001 : insuredPerson.getInsuredBeneficiaryList()) {
				insuredBeneficiaryList.add(new GInsuredPersonBeneficiaries(mbp001));
			}
		}
		this.insuredPersonBeneficiariesList = insuredBeneficiaryList;

		List<GInsuredPersonAddon> insuredPersonAddOnList = new ArrayList<GInsuredPersonAddon>();
		if (insuredPerson.getInsuredPersonAddOnList() != null) {
			for (MIPA001 mipa001 : insuredPerson.getInsuredPersonAddOnList()) {
				insuredPersonAddOnList.add(new GInsuredPersonAddon(mipa001));
			}
		}
		this.insuredPersonAddOnList = insuredPersonAddOnList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MOBILE_PERSONALACCIDENT_INSURED_PERSON_GEN")
	@Access(value = AccessType.PROPERTY)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = FormatID.formatId(id, getPrefix(), 10);
		}
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}

	public List<GInsuredPersonAddon> getInsuredPersonAddOnList() {
		return insuredPersonAddOnList;
	}

	public void setInsuredPersonAddOnList(List<GInsuredPersonAddon> insuredPersonAddOnList) {
		this.insuredPersonAddOnList = insuredPersonAddOnList;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
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

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<GInsuredPersonBeneficiaries> getInsuredPersonBeneficiariesList() {

		if (this.insuredPersonBeneficiariesList == null) {
			this.insuredPersonBeneficiariesList = new ArrayList<GInsuredPersonBeneficiaries>();
		}
		return insuredPersonBeneficiariesList;

	}

	public void setInsuredPersonBeneficiariesList(List<GInsuredPersonBeneficiaries> insuredPersonBeneficiariesList) {
		this.insuredPersonBeneficiariesList = insuredPersonBeneficiariesList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
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
		result = prime * result + ((occupationId == null) ? 0 : occupationId.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		long temp;
		temp = Double.doubleToLongBits(premium);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		temp = Double.doubleToLongBits(sumInsured);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		MobilePersonalAccidentInsuredPerson other = (MobilePersonalAccidentInsuredPerson) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
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
		if (occupationId == null) {
			if (other.occupationId != null)
				return false;
		} else if (!occupationId.equals(other.occupationId))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (Double.doubleToLongBits(premium) != Double.doubleToLongBits(other.premium))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (Double.doubleToLongBits(sumInsured) != Double.doubleToLongBits(other.sumInsured))
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
