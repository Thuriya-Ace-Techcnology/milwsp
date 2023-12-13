package org.ace.insurance.life.dto;



import java.util.ArrayList;
import java.util.List;

import org.ace.insurance.agent.OutboundAssociationAgent;
import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.insurance.life.dao.entities.ProposalInsuredPerson;
import org.ace.insurance.system.common.branch.Branch;
import org.ace.insurance.system.common.paymenttype.PaymentType;

public class LifeProposalDTO {
    
	private String orderId;
    private long submittedDate;
    private String branchId;
    private Branch branch;
	private PaymentType paymentType;
    private String paymentTypeId;
    private OutboundAssociationAgent authorizeAssociation;
	private String authorizeAssociationId;
    private long startDate;
    private long endDate;
    private int periodMonth;
    private BuyerPlatForm buyerPlatForm;

	private List<InsuredPersonInfoDTO> proposalInsuredPersonDTOList;
	
	
	public LifeProposalDTO(LifeProposal lifeProposal) {
		this.orderId = lifeProposal.getOrderId();
		this.submittedDate = lifeProposal.getSubmittedDate().getTime();
		this.branchId = lifeProposal.getBranch().getId();
		this.paymentTypeId = lifeProposal.getPaymentType().getId();
		if(lifeProposal.getAssociationAgent() != null) {
			this.authorizeAssociationId = lifeProposal.getAssociationAgent().getId();
		}		
		this.startDate = lifeProposal.getStartDate().getTime();
		this.endDate = lifeProposal.getEndDate().getTime();
		this.periodMonth = lifeProposal.getPeriodMonth();
		this.buyerPlatForm = lifeProposal.getBuyerPlatForm();
		for(ProposalInsuredPerson proposalInsuredPerson : lifeProposal.getProposalInsuredPersonList()) {
			addInsuredPersonInfoDTO(new InsuredPersonInfoDTO(proposalInsuredPerson));
		}	
		
	}
	
	public void addInsuredPersonInfoDTO(InsuredPersonInfoDTO personInfoDTO) {
		if (this.proposalInsuredPersonDTOList == null) {
			proposalInsuredPersonDTOList = new ArrayList<InsuredPersonInfoDTO>();
		}
		this.proposalInsuredPersonDTOList.add(personInfoDTO);
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
	public String getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public String getAuthorizeAssociationId() {
		return authorizeAssociationId;
	}
	public void setAuthorizeAssociationId(String authorizeAssociationId) {
		this.authorizeAssociationId = authorizeAssociationId;
	}
	public long getStartDate() {
		return startDate;
	}
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	
    public int getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(int periodMonth) {
		this.periodMonth = periodMonth;
	}

	public List<InsuredPersonInfoDTO> getProposalInsuredPersonDTOList() {
		return proposalInsuredPersonDTOList;
	}
	public void setProposalInsuredPersonDTO(List<InsuredPersonInfoDTO> proposalInsuredPersonDTOList) {
		this.proposalInsuredPersonDTOList = proposalInsuredPersonDTOList;
	}
	
    public BuyerPlatForm getBuyerPlatForm() {
		return buyerPlatForm;
	}
	public void setBuyerPlatForm(BuyerPlatForm buyerPlatForm) {
		this.buyerPlatForm = buyerPlatForm;
	}
	
    public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	
    public OutboundAssociationAgent getAuthorizeAssociation() {
		return authorizeAssociation;
	}
	public void setAuthorizeAssociation(OutboundAssociationAgent authorizeAssociation) {
		this.authorizeAssociation = authorizeAssociation;
	}

    
    

}
