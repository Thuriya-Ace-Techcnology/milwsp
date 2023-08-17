package org.ace.ws.model.thirdParty;

import org.ace.insurance.common.IdType;

public class TPDInfoDTO {

	private IdType idType;
	private String idNo;
	private String name;
	private String address;
	private String driverCodeNo;
	private String policyNo;
	private String contactNo;
	private String dob;
	private String startDate;
	private String endDate;
	private double premium;
	private int periodOfYear;
	private long typeOfDriverId;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDriverCodeNo() {
		return driverCodeNo;
	}

	public void setDriverCodeNo(String driverCodeNo) {
		this.driverCodeNo = driverCodeNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public int getPeriodOfYear() {
		return periodOfYear;
	}

	public void setPeriodOfYear(int periodOfYear) {
		this.periodOfYear = periodOfYear;
	}

	public long getTypeOfDriverId() {
		return typeOfDriverId;
	}

	public void setTypeOfDriverId(long typeOfDriverId) {
		this.typeOfDriverId = typeOfDriverId;
	}

}
