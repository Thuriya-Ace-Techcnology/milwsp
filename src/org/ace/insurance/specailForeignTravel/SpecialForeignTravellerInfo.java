package org.ace.insurance.specailForeignTravel;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ace.insurance.common.Gender;
import org.ace.insurance.common.MaritalStatus;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;

@Embeddable
public class SpecialForeignTravellerInfo {
	private String countryCode;
	private String fullName;
	private Date dateOfBirth;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String passportNo;
	private String contactNo;
	private String myanmarAddress;
	private String residentAddress;
	private int age;
	private String email;
	private Date passportExpireDate;
	private String residentCountry;
	private String journeyFrom;
	private String journeyTo;
	private String fatherName;
	private String race;
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	private Date departureDate;
	private String occupation;
	private String foreignContactNo;
	
	
	public SpecialForeignTravellerInfo() {
		
	}
	public SpecialForeignTravellerInfo(SpecialForeignTravelDTO dto) {
		this.countryCode = dto.getCountryCode();
		this.fullName = dto.getFullName();
		this.age =dto.getAge();
		this.passportNo = dto.getPassportNo();
		this.gender = dto.getGender();
		this.contactNo = dto.getContactNo();
		this.email  = dto.getEmail();
		this.passportExpireDate =dto.getPassportExpireDate() == 0 ? null :  new Date(dto.getPassportExpireDate());
		this.dateOfBirth =dto.getDateOfBirth() == 0 ? null : new Date(dto.getDateOfBirth());
		this.journeyFrom = dto.getJourneyFrom();
		this.myanmarAddress = dto.getMyanmarAddress();
		this.residentAddress = dto.getResidentAddress();
		this.residentCountry = dto.getResidentCountry();
		this.journeyTo=dto.getJourneyTo();
		this.fatherName=dto.getFatherName();
		this.race=dto.getRace();
		this.maritalStatus=dto.getMaritalStatus();
		this.departureDate=dto.getDepartureDate()==0 ? null:new Date(dto.getDepartureDate());
		this.occupation=dto.getOccupation();
		this.foreignContactNo=dto.getForeignContactNo();
	}
	
	
	
	
	public String getJourneyTo() {
		return journeyTo;
	}
	public void setJourneyTo(String journeyTo) {
		this.journeyTo = journeyTo;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getForeignContactNo() {
		return foreignContactNo;
	}
	public void setForeignContactNo(String foreignContactNo) {
		this.foreignContactNo = foreignContactNo;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getPassportExpireDate() {
		return passportExpireDate;
	}
	public void setPassportExpireDate(Date passportExpireDate) {
		this.passportExpireDate = passportExpireDate;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getJourneyFrom() {
		return journeyFrom;
	}
	public void setJourneyFrom(String journeyFrom) {
		this.journeyFrom = journeyFrom;
	}
	public String getMyanmarAddress() {
		return myanmarAddress;
	}
	public void setMyanmarAddress(String myanmarAddress) {
		this.myanmarAddress = myanmarAddress;
	}
	public String getResidentAddress() {
		return residentAddress;
	}
	public void setResidentAddress(String residentAddress) {
		this.residentAddress = residentAddress;
	}
	
	public String getResidentCountry() {
		return residentCountry;
	}
	public void setResidentCountry(String residentCountry) {
		this.residentCountry = residentCountry;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((contactNo == null) ? 0 : contactNo.hashCode());
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((journeyFrom == null) ? 0 : journeyFrom.hashCode());
		result = prime * result + ((myanmarAddress == null) ? 0 : myanmarAddress.hashCode());
		result = prime * result + ((passportExpireDate == null) ? 0 : passportExpireDate.hashCode());
		result = prime * result + ((passportNo == null) ? 0 : passportNo.hashCode());
		result = prime * result + ((residentAddress == null) ? 0 : residentAddress.hashCode());
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
		SpecialForeignTravellerInfo other = (SpecialForeignTravellerInfo) obj;
		if (age != other.age)
			return false;
		if (contactNo == null) {
			if (other.contactNo != null)
				return false;
		} else if (!contactNo.equals(other.contactNo))
			return false;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (gender != other.gender)
			return false;
		if (journeyFrom == null) {
			if (other.journeyFrom != null)
				return false;
		} else if (!journeyFrom.equals(other.journeyFrom))
			return false;
		if (myanmarAddress == null) {
			if (other.myanmarAddress != null)
				return false;
		} else if (!myanmarAddress.equals(other.myanmarAddress))
			return false;
		if (passportExpireDate == null) {
			if (other.passportExpireDate != null)
				return false;
		} else if (!passportExpireDate.equals(other.passportExpireDate))
			return false;
		if (passportNo == null) {
			if (other.passportNo != null)
				return false;
		} else if (!passportNo.equals(other.passportNo))
			return false;
		if (residentAddress == null) {
			if (other.residentAddress != null)
				return false;
		} else if (!residentAddress.equals(other.residentAddress))
			return false;
		return true;
	}
	
	
	
}
