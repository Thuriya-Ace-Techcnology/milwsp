package org.ace.ws.model.mobilePersonalAccidentproposal;

import java.util.Date;

import org.ace.insurance.common.ProposalStatus;

public class PAInsuredPerson001 {
	private String initialId;
	private String firstName;
	private String middleName;
	private String lastName;
	private double sumInsured;
	private double premium;
	private double addOnPremium;
	private ProposalStatus proposalStatus;
	private String proposalNo;
	private String policyNo;
	private String currencyId;
	private Date submittedDate;
	private String orderId;
	// private Date createdDate;
	private int transactionFees;
	public PAInsuredPerson001() {

	}

	public PAInsuredPerson001(String initialId, String firstName, String middleName, String lastName, double sumInsured, double premium, double addOnPremium,
			ProposalStatus proposalStatus, String proposalNo, String policyNo, String currencyId, Date submittedDate,String orderId,int transactionFees) {
		super();
		this.initialId = initialId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.sumInsured = sumInsured;
		this.premium = premium;
		this.addOnPremium = addOnPremium;
		this.proposalStatus = proposalStatus;
		this.proposalNo = proposalNo;
		this.policyNo = policyNo;
		this.currencyId = currencyId;
		this.submittedDate = submittedDate;
		this.orderId = orderId;
		this.transactionFees = transactionFees;
		
	}

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public double getAddOnPremium() {
		return addOnPremium;
	}

	public void setAddOnPremium(double addOnPremium) {
		this.addOnPremium = addOnPremium;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(int transactionFees) {
		this.transactionFees = transactionFees;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
