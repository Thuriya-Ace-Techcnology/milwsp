package org.ace.ws.model.mobilePersonalAccidentproposal;

import java.util.List;

import org.ace.insurance.common.ProposalStatus;
import org.ace.ws.model.ResponseStatus;

public class MPAP001 {
	private String id;
	private String currencyId;
	private int periodMonth;
	private String transactionId;
	private String proposalNo;
	private String policyNo;
	private String userId;
	private String orderId;
	private String paymentDate;
	private int transactionFees;
	private boolean deleteStatus;
	private String paymentTypeId;
	private long submittedDate;
	private ProposalStatus proposalStatus;
	private long activedPolicyStartDate;
	private long activedPolicyEndDate;
	private List<PAIP001> insuredPersonList;
	private ResponseStatus responseStatus;
	private int version;

	
	public MPAP001() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public int getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(int transactionFees) {
		this.transactionFees = transactionFees;
	}
	public int getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(int periodMonth) {
		this.periodMonth = periodMonth;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(long submittedDate) {
		this.submittedDate = submittedDate;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
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

	public List<PAIP001> getInsuredPersonList() {
		return insuredPersonList;
	}

	public void setInsuredPersonList(List<PAIP001> insuredPersonList) {
		this.insuredPersonList = insuredPersonList;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
