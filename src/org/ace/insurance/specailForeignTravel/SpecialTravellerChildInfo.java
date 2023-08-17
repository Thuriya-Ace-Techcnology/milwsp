package org.ace.insurance.specailForeignTravel;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.ace.insurance.common.Gender;
import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;

@Embeddable
public class SpecialTravellerChildInfo {

	private int cAge;
	
	private String cName;
	
	@Enumerated(EnumType.STRING)
	private Gender cGender;
	
	private String cRelation;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date cBirthDate;
	
	private String cOtherName1;
	
	private String cOtherName2;
	
	private boolean cStatus;

	
	public SpecialTravellerChildInfo() {
		super();
	}
	
	
	public SpecialTravellerChildInfo(SpecialForeignTravelDTO dto) {
		super();
		this.cAge = dto.getcAge();
		this.cName = dto.getcName();
		this.cRelation = dto.getcRelation();
		this.cBirthDate = dto.getcBirthDate() == 0 ? null : new Date(dto.getcBirthDate());
		this.cOtherName1 = dto.getcOtherName1();
		this.cOtherName2 = dto.getcOtherName2();
		this.cStatus = dto.iscStatus();
		this.cGender = dto.getcGender();
	}


	public int getcAge() {
		return cAge;
	}

	public void setcAge(int cAge) {
		this.cAge = cAge;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcRelation() {
		return cRelation;
	}

	public void setcRelation(String cRelation) {
		this.cRelation = cRelation;
	}

	public Date getcBirthDate() {
		return cBirthDate;
	}

	public void setcBirthDate(Date cBirthDate) {
		this.cBirthDate = cBirthDate;
	}

	public String getcOtherName1() {
		return cOtherName1;
	}

	public void setcOtherName1(String cOtherName1) {
		this.cOtherName1 = cOtherName1;
	}

	public String getcOtherName2() {
		return cOtherName2;
	}

	public void setcOtherName2(String cOtherName2) {
		this.cOtherName2 = cOtherName2;
	}

	public boolean iscStatus() {
		return cStatus;
	}

	public void setcStatus(boolean cStatus) {
		this.cStatus = cStatus;
	}


	public Gender getcGender() {
		return cGender;
	}


	public void setcGender(Gender cGender) {
		this.cGender = cGender;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cAge;
		result = prime * result + ((cBirthDate == null) ? 0 : cBirthDate.hashCode());
		result = prime * result + ((cGender == null) ? 0 : cGender.hashCode());
		result = prime * result + ((cName == null) ? 0 : cName.hashCode());
		result = prime * result + ((cOtherName1 == null) ? 0 : cOtherName1.hashCode());
		result = prime * result + ((cOtherName2 == null) ? 0 : cOtherName2.hashCode());
		result = prime * result + ((cRelation == null) ? 0 : cRelation.hashCode());
		result = prime * result + (cStatus ? 1231 : 1237);
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
		SpecialTravellerChildInfo other = (SpecialTravellerChildInfo) obj;
		if (cAge != other.cAge)
			return false;
		if (cBirthDate == null) {
			if (other.cBirthDate != null)
				return false;
		} else if (!cBirthDate.equals(other.cBirthDate))
			return false;
		if (cGender != other.cGender)
			return false;
		if (cName == null) {
			if (other.cName != null)
				return false;
		} else if (!cName.equals(other.cName))
			return false;
		if (cOtherName1 == null) {
			if (other.cOtherName1 != null)
				return false;
		} else if (!cOtherName1.equals(other.cOtherName1))
			return false;
		if (cOtherName2 == null) {
			if (other.cOtherName2 != null)
				return false;
		} else if (!cOtherName2.equals(other.cOtherName2))
			return false;
		if (cRelation == null) {
			if (other.cRelation != null)
				return false;
		} else if (!cRelation.equals(other.cRelation))
			return false;
		if (cStatus != other.cStatus)
			return false;
		return true;
	}

}
