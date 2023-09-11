package org.ace.insurance.life.lifeProposal.service.interfaces;

import org.ace.insurance.life.dao.entities.LifeProposal;
public interface ILifeProposalService{
	
	public LifeProposal addLifeProposal(LifeProposal lifeProposal);
	
	public void paymentLifeProposal(LifeProposal lifeProposal);
	
	public LifeProposal findLifeProposalByOrderId(String orderId);
	

}
