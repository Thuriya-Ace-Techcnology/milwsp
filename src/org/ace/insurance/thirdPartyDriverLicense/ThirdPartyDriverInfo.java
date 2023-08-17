package org.ace.insurance.thirdPartyDriverLicense;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.ace.insurance.common.IdType;
import org.ace.insurance.common.TableName;
import org.ace.insurance.common.UserRecorder;

@Entity
@Table(name = TableName.THIRDPARTY_DRIVER_INFO)
public class ThirdPartyDriverInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private IdType idType;
	private String idNo;
	private String name;
	private String address;
	private String driverCodeNo;
	private String contactNo;
	private String dob;

	private String startDate;

	private String endDate;

	private double premium;

	private int periodOfYear;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPEOFDRIVERID", referencedColumnName = "ID")
	private TypeOfDriver typeOfDriver;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THIRDPARTYPROPOSALID", referencedColumnName = "ID")
	private ThirdPartyDriverProposal thirdPartyDriverProposal;

	public ThirdPartyDriverInfo() {
	}

	public ThirdPartyDriverInfo(Long id, IdType idType, String idNo, String name, String address, String driverCodeNo,
			String contactNo, String dob, String startDate, String endDate, double premium, int periodOfYear,
			TypeOfDriver typeOfDriver, ThirdPartyDriverProposal thirdPartyDriverProposal, UserRecorder recorder,
			int version) {
		super();
		this.id = id;
		this.idType = idType;
		this.idNo = idNo;
		this.name = name;
		this.address = address;
		this.driverCodeNo = driverCodeNo;
		this.contactNo = contactNo;
		this.dob = dob;
		this.startDate = startDate;
		this.endDate = endDate;
		this.premium = premium;
		this.periodOfYear = periodOfYear;
		this.typeOfDriver = typeOfDriver;
		this.thirdPartyDriverProposal = thirdPartyDriverProposal;
		this.recorder = recorder;
		this.version = version;
	}

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDriverCodeNo() {
		return driverCodeNo;
	}

	public void setDriverCodeNo(String driverCodeNo) {
		this.driverCodeNo = driverCodeNo;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
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

	public int getPeriodOfYear() {
		return periodOfYear;
	}

	public void setPeriodOfYear(int periodOfYear) {
		this.periodOfYear = periodOfYear;
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

	public TypeOfDriver getTypeOfDriver() {
		return typeOfDriver;
	}

	public void setTypeOfDriver(TypeOfDriver typeOfDriver) {
		this.typeOfDriver = typeOfDriver;
	}

	public ThirdPartyDriverProposal getThirdPartyDriverProposal() {
		return thirdPartyDriverProposal;
	}

	public void setThirdPartyDriverProposal(ThirdPartyDriverProposal thirdPartyDriverProposal) {
		this.thirdPartyDriverProposal = thirdPartyDriverProposal;
	}

}
