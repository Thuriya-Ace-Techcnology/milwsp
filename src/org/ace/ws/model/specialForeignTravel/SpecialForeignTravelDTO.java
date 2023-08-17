package org.ace.ws.model.specialForeignTravel;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.CoveragePlan;
import org.ace.insurance.common.Gender;
import org.ace.insurance.common.MaritalStatus;
import org.ace.insurance.common.Packages;
import org.ace.insurance.common.PaymentGateway;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.SaleChannelType;
import org.ace.insurance.common.TourismType;
import org.ace.insurance.specailForeignTravel.OutboundAssociationAgent;

public class SpecialForeignTravelDTO {

	private String id;

	private double transactionFees;

	private double premium;

	private int periodMonth;

	private String policyNo;

	private String currencyId;

	private boolean deleteStatus;

	private String orderId;
	
	private String agentId;

	private String agentName;
	
	private long submittedDate;

	private long paymentDate;
	
	private SaleChannelType saleChannelType;
	
	private PaymentGateway paymentGateWay;

	private long activedPolicyStartDate;

	private long activedPolicyEndDate;

	private String countryCode;

	private String residentCountry;

	private String fullName;

	private int age;

	private String passportNo;

	private Gender gender;

	private String contactNo;

	private String email;

	private String passportIssuedCountry;
	
	private long passportIssuedDate;
	
	private long passportExpireDate;

	private long dateOfBirth;

	private String jasperDob;
	
	private String cJasperDob;

	private ProposalStatus proposalStatus;

	private String journeyFrom;
	private String journeyTo;
	private String fatherName;
	private String race;
	private MaritalStatus maritalStatus;
	private long departureDate;
	private String occupation;
	private String foreignContactNo;

	private String myanmarAddress;

	private String residentAddress;
	
	private double sumInsured;

	private String benName;

	private long benDateOfBirth;

	private String benNIDNo;

	private String benRelationship;

	private String benResidentAddress;

	private String benContactNo;

	private String benEmail;

	private String benCode;

	private String benResidentCountry;

	private int cAge;
	
	private String cName;
	
	private String cRelation;
	
	private long cBirthDate;
	
	private String cOtherName1;
	
	private String cOtherName2;
	
	private boolean cStatus;
	
	private Gender cGender;
	
	private BuyerPlatForm buyerPlatForm;
	
	private TourismType tourismType;
	
	private String registrationNo;
	
	private double tpaFee;
	
	private double agentCommission;
	
	private String agentLicenseNo;
	
	private String cancellationReason;
	
	private boolean ulinkStatus;
	
	private boolean ulinkEdit;
	
	private boolean onlineEdit;
	
	private String associationAgentId;
	
	@Enumerated(EnumType.STRING)
	private CoveragePlan coveragePlan;
	
	@Enumerated(EnumType.STRING)
	private Packages packages;
	
	private String productId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(double transactionFees) {
		this.transactionFees = transactionFees;
	}
	
	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public SaleChannelType getSaleChannelType() {
		return saleChannelType;
	}

	public void setSaleChannelType(SaleChannelType saleChannelType) {
		this.saleChannelType = saleChannelType;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public int getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(int periodMonth) {
		this.periodMonth = periodMonth;
	}
	
	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public TourismType getTourismType() {
		return tourismType;
	}

	public void setTourismType(TourismType tourismType) {
		this.tourismType = tourismType;
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

	public long getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(long departureDate) {
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

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public long getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(long submittedDate) {
		this.submittedDate = submittedDate;
	}

	public long getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(long paymentDate) {
		this.paymentDate = paymentDate;
	}

	public long getActivedPolicyStartDate() {
		return activedPolicyStartDate;
	}

	public void setActivedPolicyStartDate(long activedPolicyStartDate) {
		this.activedPolicyStartDate = activedPolicyStartDate;
	}

	public long getActivedPolicyEndDate() {
		return activedPolicyEndDate;
	}

	public void setActivedPolicyEndDate(long activedPolicyEndDate) {
		this.activedPolicyEndDate = activedPolicyEndDate;
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

	public PaymentGateway getPaymentGateWay() {
		return paymentGateWay;
	}

	public void setPaymentGateWay(PaymentGateway paymentGateWay) {
		this.paymentGateWay = paymentGateWay;
	}

	public long getPassportExpireDate() {
		return passportExpireDate;
	}

	public void setPassportExpireDate(long passportExpireDate) {
		this.passportExpireDate = passportExpireDate;
	}

	public long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public String getPassportIssuedCountry() {
		return passportIssuedCountry;
	}

	public void setPassportIssuedCountry(String passportIssuedCountry) {
		this.passportIssuedCountry = passportIssuedCountry;
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

	public String getBenName() {
		return benName;
	}

	public void setBenName(String benName) {
		this.benName = benName;
	}

	public long getBenDateOfBirth() {
		return benDateOfBirth;
	}

	public void setBenDateOfBirth(long benDateOfBirth) {
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

	public String getJasperDob() {
		LocalDate date = Instant.ofEpochMilli(dateOfBirth).atZone(ZoneId.systemDefault()).toLocalDate();
		jasperDob = date.getDayOfMonth()+" "+date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)+" "+date.getYear();
		return jasperDob;
	}

	public String getcJasperDob() {
		LocalDate date = Instant.ofEpochMilli(cBirthDate).atZone(ZoneId.systemDefault()).toLocalDate();
		cJasperDob = date.getDayOfMonth()+" "+date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)+" "+date.getYear();
		return cJasperDob;
	}

	public String getResidentCountry() {
		return residentCountry;
	}

	public void setResidentCountry(String residentCountry) {
		this.residentCountry = residentCountry;
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

	public void setJasperDob(String jasperDob) {
		this.jasperDob = jasperDob;
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

	public long getcBirthDate() {
		return cBirthDate;
	}

	public void setcBirthDate(long cBirthDate) {
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

	public BuyerPlatForm getBuyerPlatForm() {
		return buyerPlatForm;
	}

	public void setBuyerPlatForm(BuyerPlatForm buyerPlatForm) {
		this.buyerPlatForm = buyerPlatForm;
	}

	public double getTpaFee() {
		return tpaFee;
	}

	public void setTpaFee(double tpaFee) {
		this.tpaFee = tpaFee;
	}

	public double getAgentCommission() {
		return agentCommission;
	}

	public void setAgentCommission(double agentCommission) {
		this.agentCommission = agentCommission;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentLicenseNo() {
		return agentLicenseNo;
	}

	public void setAgentLicenseNo(String agentLicenseNo) {
		this.agentLicenseNo = agentLicenseNo;
	}

	public long getPassportIssuedDate() {
		return passportIssuedDate;
	}

	public void setPassportIssuedDate(long passportIssuedDate) {
		this.passportIssuedDate = passportIssuedDate;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public boolean isUlinkStatus() {
		return ulinkStatus;
	}

	public void setUlinkStatus(boolean ulinkStatus) {
		this.ulinkStatus = ulinkStatus;
	}

	public boolean isUlinkEdit() {
		return ulinkEdit;
	}

	public void setUlinkEdit(boolean ulinkEdit) {
		this.ulinkEdit = ulinkEdit;
	}

	public boolean isOnlineEdit() {
		return onlineEdit;
	}

	public void setOnlineEdit(boolean onlineEdit) {
		this.onlineEdit = onlineEdit;
	}

	public CoveragePlan getCoveragePlan() {
		return coveragePlan;
	}

	public void setCoveragePlan(CoveragePlan coveragePlan) {
		this.coveragePlan = coveragePlan;
	}

	public Packages getPackages() {
		return packages;
	}

	public void setPackages(Packages packages) {
		this.packages = packages;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public double getTotalPremium() {
		return premium + tpaFee + agentCommission;
	}

	public String getAssociationAgentId() {
		return associationAgentId;
	}

	public void setAssociationAgentId(String associationAgentId) {
		this.associationAgentId = associationAgentId;
	}

}
