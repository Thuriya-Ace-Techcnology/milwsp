package org.ace.ws.model.onlineBiller;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.Utils;

public class OnlineBillerDTO {
	
	private String id;
	private String invoiceNo;
	private String policyNo;
	private String policyOwnerName;
	private String agentName;
	private String licenceNo;
	private String interest;
	private String department;
	private double stampFees;
	private double premium;
	private BuyerPlatForm buyerPlatForm;
	private ProposalStatus proposalStatus;
	private double sumInsured;
	private boolean locked;
	private boolean bought;
	private String activedPolicyStartDate;
	private String activedPolicyEndDate;
	private String submittedDate;
	private String paymentDate;
	private String orderId;
	private double serviceCharges;		
	public OnlineBillerDTO() {
		
	}
	public OnlineBillerDTO(OnlineBiller dto) {
		this.id = dto.getId();
		this.invoiceNo = dto.getInvoiceNo();
		this.policyNo = dto.getPolicyNo();
		this.policyOwnerName = dto.getPolicyOwnerName();
		this.agentName = dto.getAgentName();
		this.licenceNo = dto.getLisceneceNo();
		this.interest = dto.getInterest();
		this.department = dto.getDepartment();
		this.premium = dto.getPremium();
		this.sumInsured = dto.getSumInsured();
		this.locked = dto.isLocked();
		this.activedPolicyStartDate = Utils.getDateFormatString(dto.getActivedPolicyStartDate());
		this.activedPolicyEndDate = Utils.getDateFormatString(dto.getActivedPolicyEndDate());
		this.submittedDate = Utils.getDateFormatString(dto.getSubmittedDate());

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolicyOwnerName() {
		return policyOwnerName;
	}

	public void setPolicyOwnerName(String policyOwnerName) {
		this.policyOwnerName = policyOwnerName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getLisceneceNo() {
		return licenceNo;
	}

	public void setLisceneceNo(String lisceneceNo) {
		this.licenceNo = lisceneceNo;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getActivedPolicyStartDate() {
		return activedPolicyStartDate;
	}

	public void setActivedPolicyStartDate(String activedPolicyStartDate) {
		this.activedPolicyStartDate = activedPolicyStartDate;
	}

	public String getActivedPolicyEndDate() {
		return activedPolicyEndDate;
	}

	public void setActivedPolicyEndDate(String activedPolicyEndDate) {
		this.activedPolicyEndDate = activedPolicyEndDate;
	}

	public String getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public double getStampFees() {
		return stampFees;
	}

	public void setStampFees(double stampFees) {
		this.stampFees = stampFees;
	}

	public BuyerPlatForm getBuyerPlatForm() {
		return buyerPlatForm;
	}

	public void setBuyerPlatForm(BuyerPlatForm buyerPlatForm) {
		this.buyerPlatForm = buyerPlatForm;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public boolean isBought() {
		return bought;
	}

	public void setBought(boolean bought) {
		this.bought = bought;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	public double getServiceCharges() {
		return serviceCharges;
	}
	public void setServiceCharges(double serviceCharges) {
		this.serviceCharges = serviceCharges;
	}

}
