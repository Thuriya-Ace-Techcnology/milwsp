package org.ace.ws.model.mobiletravelproposal;

import java.util.List;

import org.ace.insurance.common.PlatFormType;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.system.productTypeRecords.dto.ProductTypeRecordsDTO;
import org.ace.ws.model.ResponseStatus;

public class MTP001 {

	private String id;
	private String userId;
	private String productId;
	private String proposalNo;
	private String policyNo;
	private String transactionId;
	private String submittedDate;
	private String paymentDate;
	private String orderId;
	private ProposalStatus proposalStatus;
	private boolean airProduct;
	private boolean deleteStatus;
	private int transactionFees;
	private boolean overSea;
	private String productType;
	private List<MIP001> insuredPersonList;
	private ResponseStatus responseStatus;
	private PlatFormType platFormType;
	private ProductTypeRecordsDTO recordsDTO;
	public MTP001() {

	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	
	public ProductTypeRecordsDTO getRecordsDTO() {
		return recordsDTO;
	}

	public void setRecordsDTO(ProductTypeRecordsDTO recordsDTO) {
		this.recordsDTO = recordsDTO;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isOverSea() {
		return overSea;
	}

	public void setOverSea(boolean overSea) {
		this.overSea = overSea;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the proposalNo
	 */
	public String getProposalNo() {
		return proposalNo;
	}

	/**
	 * @param proposalNo
	 *            the proposalNo to set
	 */
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	/**
	 * @return the submittedDate
	 */
	public String getSubmittedDate() {
		return submittedDate;
	}

	/**
	 * @param submittedDate
	 *            the submittedDate to set
	 */
	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}

	/**
	 * @return the proposalStatus
	 */
	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public boolean isAirProduct() {
		return airProduct;
	}

	public void setAirProduct(boolean airProduct) {
		this.airProduct = airProduct;
	}

	/**
	 * @param proposalStatus
	 *            the proposalStatus to set
	 */
	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	/**
	 * @return the inuredPersonList
	 */
	public List<MIP001> getInsuredPersonList() {
		return insuredPersonList;
	}

	/**
	 * @param inuredPersonList
	 *            the inuredPersonList to set
	 */
	public void setInsuredPersonList(List<MIP001> insuredPersonList) {
		this.insuredPersonList = insuredPersonList;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public int getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(int transactionFees) {
		this.transactionFees = transactionFees;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public PlatFormType getPlatFormType() {
		return platFormType;
	}

	public void setPlatFormType(PlatFormType platFormType) {
		this.platFormType = platFormType;
	}

}
