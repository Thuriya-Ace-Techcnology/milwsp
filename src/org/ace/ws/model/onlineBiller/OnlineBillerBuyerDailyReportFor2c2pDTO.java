package org.ace.ws.model.onlineBiller;

import java.util.Date;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.ProposalStatus;

public class OnlineBillerBuyerDailyReportFor2c2pDTO {
	private String invoiceNo;
	private String policyNo;
	private String policyOwnerName;
	private double stampfees;
	private String department;
	private ProposalStatus proposalStatus;
	private double premium;
	private BuyerPlatForm buyerPlatForm;
	private Date submittedDate;
	private String orderId;
	private Date paymentDate;
	private Date fromDate;
	private Date toDate;
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
	public double getStampfees() {
		return stampfees;
	}
	public void setStampfees(double stampfees) {
		this.stampfees = stampfees;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public BuyerPlatForm getBuyerPlatForm() {
		return buyerPlatForm;
	}
	public void setBuyerPlatForm(BuyerPlatForm buyerPlatForm) {
		this.buyerPlatForm = buyerPlatForm;
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
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
