package org.ace.insurance.life.dto;

import java.util.ArrayList;
import java.util.List;
import org.ace.insurance.agent.OutboundAssociationAgent;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.life.dao.entities.LifePolicy;
import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.insurance.life.dao.entities.PolicyInsuredPerson;
import org.ace.insurance.system.common.paymenttype.PaymentType;

public class LifePolicyDTO {
	
	private String id;
	private String policyNo;
	private long policyStartDate;
	private long policyEndDate; 
    private String paymentType;
	private int periodOfYear;
	private LifeProposal proposal;
	private InsuredPersonInfoDTO policyInsuredPersonDTO;
	private List<InsuredPersonInfoDTO> policyInsuredPersonDTOList;
	
	
	public LifePolicyDTO() {
	}
	
	public LifePolicyDTO(LifePolicy policy) {
		this.id = policy.getId();
		this.policyNo = policy.getPolicyNo();
		this.policyStartDate = policy.getActivedPolicyStartDate().getTime();
		this.policyEndDate = policy.getActivedPolicyEndDate().getTime();
		this.paymentType = policy.getPaymentType().getName();
		this.periodOfYear = policy.getPeriodOfYears();
		
		for (PolicyInsuredPerson insured : policy.getPolicyInsuredPersonList()) {
			addInsuredPersonInfoDTO(new InsuredPersonInfoDTO(insured));
		}
	}

	public void addInsuredPersonInfoDTO(InsuredPersonInfoDTO dto) {
		getInsuredPersonInfoDTOList().add(dto);
	}
	
	private List<InsuredPersonInfoDTO> getInsuredPersonInfoDTOList() {
		if(policyInsuredPersonDTOList == null) {
			policyInsuredPersonDTOList = new ArrayList<InsuredPersonInfoDTO>();
		}
		return policyInsuredPersonDTOList;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public long getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(long policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public long getPolicyEndDate() {
		return policyEndDate;
	}
	public void setPolicyEndDate(long policyEndDate) {
		this.policyEndDate = policyEndDate;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public LifeProposal getProposal() {
		return proposal;
	}
	public void setProposal(LifeProposal proposal) {
		this.proposal = proposal;
	}
	
	public int getPeriodOfYear() {
		return periodOfYear;
		
	}
	public void setPeriodOfYear(int periodOfYear) {
		this.periodOfYear = periodOfYear;
		
	}

	public List<InsuredPersonInfoDTO> getPolicyInsuredPersonDTOList() {
		return policyInsuredPersonDTOList;
		
	}

	public void setPolicyInsuredPersonDTOList(List<InsuredPersonInfoDTO> policyInsuredPersonDTOList) {
		this.policyInsuredPersonDTOList = policyInsuredPersonDTOList;
		
	}

	public InsuredPersonInfoDTO getPolicyInsuredPersonDTO() {
		return policyInsuredPersonDTO;
		
	}

	public void setPolicyInsuredPersonDTO(InsuredPersonInfoDTO policyInsuredPersonDTO) {
		this.policyInsuredPersonDTO = policyInsuredPersonDTO;
		
	}

}
