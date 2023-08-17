package org.ace.ws.model.mobilePersonalAccidentproposal;

import org.ace.insurance.common.ProposalStatus;

public class PAInsuredPerson002 {
	private String insuredPersonName;
	private String currencyId;
	private double sumInsured;
	private double premium;
	private ProposalStatus proposalStatus;
	private String proposalNo;
	private String policyNo;
	private long submittedDate;
	private String orderId;
	private int transactionFees;
	
	// private Date createdDate;

	public PAInsuredPerson002() {

	}

	public PAInsuredPerson002(String insuredPersonName, double sumInsured, double premium, ProposalStatus proposalStatus, String proposalNo, String policyNo, String currencyId,
			long submittedDate,String orderId,int transactionFees) {

		this.insuredPersonName = insuredPersonName;
		this.sumInsured = sumInsured;
		this.premium = premium;
		this.proposalStatus = proposalStatus;
		this.proposalNo = proposalNo;
		this.policyNo = policyNo;
		this.submittedDate = submittedDate;
		this.currencyId = currencyId;
		this.orderId = orderId;
		this.transactionFees = transactionFees;
	}

	public String getInsuredPersonName() {
		return insuredPersonName;
	}

	public void setInsuredPersonName(String insuredPersonName) {
		this.insuredPersonName = insuredPersonName;
	}

	public int getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(int transactionFees) {
		this.transactionFees = transactionFees;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
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

	public long getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(long submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
