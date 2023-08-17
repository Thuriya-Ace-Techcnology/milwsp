package org.ace.ws.model.mobileMedicalproposal;

import java.util.Date;

import org.ace.insurance.common.ProposalStatus;

public class InsuredPersonDTO {
	private String initialId;
	private String firstName;
	private String middleName;
	private String lastName;
	private int unit;
	private double premium;
	private double addOnPremium;
	private ProposalStatus proposalStatus;
	private String proposalNo;
	private String policyNo;
	private Date submittedDate;
	private String orderId;
	private int transactionFees;

	public InsuredPersonDTO() {

	}

	public InsuredPersonDTO(String initialId, String firstName, String middleName, String lastName, int unit, double premium, double addOnPremium, ProposalStatus proposalStatus,
			String proposalNo, String policyNo, Date submittedDate, String orderId, int transactionFees) {

		this.initialId = initialId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.unit = unit;
		this.premium = premium;
		this.addOnPremium = addOnPremium;
		this.proposalStatus = proposalStatus;
		this.proposalNo = proposalNo;
		this.policyNo = policyNo;
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

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
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
