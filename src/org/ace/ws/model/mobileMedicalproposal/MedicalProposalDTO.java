package org.ace.ws.model.mobileMedicalproposal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ace.insurance.common.CustomerType;
import org.ace.insurance.common.HealthType;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.Utils;
import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.insurance.medical.MobileMedicalProposalInsuredPerson;
import org.ace.insurance.system.productTypeRecords.dto.ProductTypeRecordsDTO;
import org.ace.ws.model.ResponseStatus;

public class MedicalProposalDTO {
	private String id;
	private int periodMonth;
	private String transactionId;
	private int transactionFees;
	private String proposalNo;
	private String policyNo;
	private String userId;
	private String orderId;
	private String paymentDate;
	private String productId;
	private String paymentTypeId;
	private String submittedDate;
	private ProposalStatus proposalStatus;
	private String activedPolicyStartDate;
	private String activedPolicyEndDate;

	private List<MedicalProposalInsuredPersonDTO> insuredPersonList;
	private ResponseStatus responseStatus;
	private HealthType healthType;
	private CustomerType customerType;
	private ProductTypeRecordsDTO recordsDTO;
	private int version;

	public MedicalProposalDTO() {

	}

	public MedicalProposalDTO(MobileMedicalProposal medicialProposal) {
	 	medicialProposal.getSubmittedDate().setDate(medicialProposal.getSubmittedDate().getDate());
	 	medicialProposal.getActivedPolicyStartDate().setDate(medicialProposal.getActivedPolicyStartDate().getDate());
	 	medicialProposal.getActivedPolicyEndDate().setDate(medicialProposal.getActivedPolicyEndDate().getDate());
		this.id = medicialProposal.getId();
		this.periodMonth = medicialProposal.getPeriodMonth();
		this.transactionId = medicialProposal.getTransactionId();
		this.proposalNo = medicialProposal.getProposalNo();
		this.policyNo = medicialProposal.getPolicyNo();
		this.userId = medicialProposal.getUserId();
		this.transactionFees = medicialProposal.getTransactionFees();
		this.orderId = medicialProposal.getOrderId();
		this.paymentTypeId = medicialProposal.getPaymentTypeId();
		this.submittedDate = medicialProposal.getSubmittedDate()!= null ? Utils.getDateFormatString(medicialProposal.getSubmittedDate()) : null;
		this.proposalStatus = medicialProposal.getProposalStatus();
		this.activedPolicyStartDate = medicialProposal.getActivedPolicyStartDate()!= null ? Utils.getDateFormatString(medicialProposal.getActivedPolicyStartDate()) : null;
		this.activedPolicyEndDate = medicialProposal.getActivedPolicyEndDate()!= null ? Utils.getDateFormatString(medicialProposal.getActivedPolicyEndDate()) : null;
		this.healthType = medicialProposal.getHealthType();
		this.customerType = medicialProposal.getCustomerType();
		this.version = medicialProposal.getVersion();
		if (medicialProposal.getMedicalProposalInsuredPersonList() != null) {
			insuredPersonList = new ArrayList<MedicalProposalInsuredPersonDTO>();
			for (MobileMedicalProposalInsuredPerson mb001 : medicialProposal.getMedicalProposalInsuredPersonList()) {
				insuredPersonList.add(new MedicalProposalInsuredPersonDTO(mb001));
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	

	public String getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
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

	public List<MedicalProposalInsuredPersonDTO> getInsuredPersonList() {
		return insuredPersonList;
	}

	public void setInsuredPersonList(List<MedicalProposalInsuredPersonDTO> insuredPersonList) {
		this.insuredPersonList = insuredPersonList;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public HealthType getHealthType() {
		return healthType;
	}

	public void setHealthType(HealthType healthType) {
		this.healthType = healthType;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ProductTypeRecordsDTO getRecordsDTO() {
		return recordsDTO;
	}

	public void setRecordsDTO(ProductTypeRecordsDTO recordsDTO) {
		this.recordsDTO = recordsDTO;
	}
	

}
