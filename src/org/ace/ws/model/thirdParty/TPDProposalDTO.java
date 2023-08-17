package org.ace.ws.model.thirdParty;

import java.util.List;

import org.ace.insurance.common.ProposalStatus;

public class TPDProposalDTO {

	private Long id;
	private String proposalNo;
	private String submittedDate;
	private String orderId;
	private String paymentDate;
	private String currencyId;
	private String branchId;
	private ProposalStatus proposalStatus;
	private List<TPDInfoDTO> driverInfoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public List<TPDInfoDTO> getDriverInfoList() {
		return driverInfoList;
	}

	public void setDriverInfoList(List<TPDInfoDTO> driverInfoList) {
		this.driverInfoList = driverInfoList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
