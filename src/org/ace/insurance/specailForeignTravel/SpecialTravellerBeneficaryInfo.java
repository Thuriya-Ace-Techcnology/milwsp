package org.ace.insurance.specailForeignTravel;

import java.util.Date;

import javax.persistence.Embeddable;


import org.ace.ws.model.specialForeignTravel.SpecialForeignTravelDTO;

@Embeddable
public class SpecialTravellerBeneficaryInfo {
	private String benName;

	private Date benDateOfBirth;

	private String benNIDNo;

	private String benRelationship;

	private String benResidentAddress;

	private String benContactNo;

	private String benEmail;

	private String benCode;
	
	private String benResidentCountry;
	
	public SpecialTravellerBeneficaryInfo() {
		super();
	}

	public SpecialTravellerBeneficaryInfo(SpecialForeignTravelDTO dto) {
		super();
		this.benName = dto.getBenName();
		this.benDateOfBirth =dto.getBenDateOfBirth() == 0 ? null : new Date(dto.getBenDateOfBirth());
		this.benNIDNo = dto.getBenNIDNo();
		this.benRelationship = dto.getBenRelationship();
		this.benResidentAddress = dto.getBenResidentAddress();
		this.benContactNo = dto.getBenContactNo();
		this.benEmail = dto.getBenEmail();
		this.benCode=dto.getBenCode();
		this.benResidentCountry = dto.getBenResidentCountry();
	}



	public String getBenName() {
		return benName;
	}

	public void setBenName(String benName) {
		this.benName = benName;
	}

	public Date getBenDateOfBirth() {
		return benDateOfBirth;
	}

	public void setBenDateOfBirth(Date benDateOfBirth) {
		this.benDateOfBirth = benDateOfBirth;
	}

	public String getBenNIDNo() {
		return benNIDNo;
	}

	public void setBenNIDNo(String benNIDNo) {
		this.benNIDNo = benNIDNo;
	}

	public String getBenRelationship() {
		return benRelationship;
	}

	public void setBenRelationship(String benRelationship) {
		this.benRelationship = benRelationship;
	}

	public String getBenResidentAddress() {
		return benResidentAddress;
	}

	public void setBenResidentAddress(String benResidentAddress) {
		this.benResidentAddress = benResidentAddress;
	}

	public String getBenContactNo() {
		return benContactNo;
	}

	public void setBenContactNo(String benContactNo) {
		this.benContactNo = benContactNo;
	}

	public String getBenEmail() {
		return benEmail;
	}

	public void setBenEmail(String benEmail) {
		this.benEmail = benEmail;
	}

	public String getBenCode() {
		return benCode;
	}

	public void setBenCode(String benCode) {
		this.benCode = benCode;
	}

	public String getBenResidentCountry() {
		return benResidentCountry;
	}

	public void setBenResidentCountry(String benResidentCountry) {
		this.benResidentCountry = benResidentCountry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((benContactNo == null) ? 0 : benContactNo.hashCode());
		result = prime * result + ((benDateOfBirth == null) ? 0 : benDateOfBirth.hashCode());
		result = prime * result + ((benEmail == null) ? 0 : benEmail.hashCode());
		result = prime * result + ((benNIDNo == null) ? 0 : benNIDNo.hashCode());
		result = prime * result + ((benName == null) ? 0 : benName.hashCode());
		result = prime * result + ((benRelationship == null) ? 0 : benRelationship.hashCode());
		result = prime * result + ((benResidentAddress == null) ? 0 : benResidentAddress.hashCode());
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
		SpecialTravellerBeneficaryInfo other = (SpecialTravellerBeneficaryInfo) obj;
		if (benContactNo == null) {
			if (other.benContactNo != null)
				return false;
		} else if (!benContactNo.equals(other.benContactNo))
			return false;
		if (benDateOfBirth == null) {
			if (other.benDateOfBirth != null)
				return false;
		} else if (!benDateOfBirth.equals(other.benDateOfBirth))
			return false;
		if (benEmail == null) {
			if (other.benEmail != null)
				return false;
		} else if (!benEmail.equals(other.benEmail))
			return false;
		if (benNIDNo == null) {
			if (other.benNIDNo != null)
				return false;
		} else if (!benNIDNo.equals(other.benNIDNo))
			return false;
		if (benName == null) {
			if (other.benName != null)
				return false;
		} else if (!benName.equals(other.benName))
			return false;
		if (benRelationship == null) {
			if (other.benRelationship != null)
				return false;
		} else if (!benRelationship.equals(other.benRelationship))
			return false;
		if (benResidentAddress == null) {
			if (other.benResidentAddress != null)
				return false;
		} else if (!benResidentAddress.equals(other.benResidentAddress))
			return false;
		return true;
	}
	
	
}
