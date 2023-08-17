package org.ace.ws.model.outboundTravel;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.CoveragePlan;
import org.ace.insurance.common.Gender;
import org.ace.insurance.common.MaritalStatus;
import org.ace.insurance.common.Packages;
import org.ace.insurance.common.PaymentGateway;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.ProposalType;
import org.ace.insurance.common.SaleChannelType;
import org.ace.insurance.common.TourismType;
import org.ace.ws.model.ResponseStatus;

public class OutboundTravelDTO {

	private String id;
	private String cloudId;
	private String proposalNo;
	private double rate;
	private String passportIssuedCountry;
	private long passportIssuedDate;
	private String registrationNo;
	private int unit;
	private double transactionFees;
	private double premium;
	private double serviceCharges;
	private int periodDay;
	private int paymentTerm;
	private String policyNo;
	private double sumInsured;
	private boolean deleteStatus;
	private boolean isConvert;
	private boolean complete;
	private String orderId;
	private long submittedDate;
	private long paymentDate;
	private long activedPolicyStartDate;
	private long activedPolicyEndDate;
	private Packages packages;
	private CoveragePlan coveragePlan;
	private SaleChannelType saleChannelType;
	private ResponseStatus responseStatus;
	private PaymentGateway paymentGateway;
	private ProposalStatus proposalStatus;
	private BuyerPlatForm buyerPlatForm;
	private TourismType tourismType;
	private ProposalType proposalType;
	
	private String countryCode;
	private String fullName;
	private long dateOfBirth;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String passportNo;
	private String contactNo;
	private String myanmarAddress;
	private String residentAddress;
	private int age;
	private String email;
	private long passportExpireDate;
	private String residentCountry;
	private String journeyFrom;
	private String journeyTo;
	private String fatherName;
	private String race;
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	private long departureDate;
	private String occupation;
	private String foreignContactNo;
	
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
	public boolean cStatus;
	@Enumerated(EnumType.STRING)
	private Gender cGender;
	private String cRelation;
	private long cBirthDate;
	private String cOtherName1;
	private String cOtherName2;

	private String agentId;
	private String agentName;
	private String branchId;
	private String paymentTypeId;
	private String currencyId;
	private int version;
	private String productId;
	private double tpaFee;
	private double agentCommission;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCloudId() {
		return cloudId;
	}
	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getPassportIssuedCountry() {
		return passportIssuedCountry;
	}
	public void setPassportIssuedCountry(String passportIssuedCountry) {
		this.passportIssuedCountry = passportIssuedCountry;
	}
	public long getPassportIssuedDate() {
		return passportIssuedDate;
	}
	public void setPassportIssuedDate(long passportIssuedDate) {
		this.passportIssuedDate = passportIssuedDate;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public double getTransactionFees() {
		return transactionFees;
	}
	public void setTransactionFees(double transactionFees) {
		this.transactionFees = transactionFees;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public double getServiceCharges() {
		return serviceCharges;
	}
	public void setServiceCharges(double serviceCharges) {
		this.serviceCharges = serviceCharges;
	}
	public int getPeriodDay() {
		return periodDay;
	}
	public void setPeriodDay(int periodDay) {
		this.periodDay = periodDay;
	}
	public int getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public double getSumInsured() {
		return sumInsured;
	}
	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}
	public boolean isDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public boolean isConvert() {
		return isConvert;
	}
	public void setConvert(boolean isConvert) {
		this.isConvert = isConvert;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
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
	public SaleChannelType getSaleChannelType() {
		return saleChannelType;
	}
	public void setSaleChannelType(SaleChannelType saleChannelType) {
		this.saleChannelType = saleChannelType;
	}
	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
	public PaymentGateway getPaymentGateway() {
		return paymentGateway;
	}
	public void setPaymentGateway(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public BuyerPlatForm getBuyerPlatForm() {
		return buyerPlatForm;
	}
	public void setBuyerPlatForm(BuyerPlatForm buyerPlatForm) {
		this.buyerPlatForm = buyerPlatForm;
	}
	public TourismType getTourismType() {
		return tourismType;
	}
	public void setTourismType(TourismType tourismType) {
		this.tourismType = tourismType;
	}
	
	public ProposalType getProposalType() {
		return proposalType;
	}
	public void setProposalType(ProposalType proposalType) {
		this.proposalType = proposalType;
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
	public long getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPassportExpireDate() {
		return passportExpireDate;
	}
	public void setPassportExpireDate(long passportExpireDate) {
		this.passportExpireDate = passportExpireDate;
	}
	public String getResidentCountry() {
		return residentCountry;
	}
	public void setResidentCountry(String residentCountry) {
		this.residentCountry = residentCountry;
	}
	public String getJourneyFrom() {
		return journeyFrom;
	}
	public void setJourneyFrom(String journeyFrom) {
		this.journeyFrom = journeyFrom;
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
	
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
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
	
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	public String getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
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
	

	
	
	

}
