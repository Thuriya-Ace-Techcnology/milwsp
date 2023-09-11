package org.ace.insurance.life.persistence.proposal.interfaces;


import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.java.component.persistence.exception.DAOException;

public interface ILifeProposalDAO {
	
	public LifeProposal insert(LifeProposal lifeProposal) throws DAOException;

	public void update(LifeProposal lifeProposal) throws DAOException;

	public void delete(LifeProposal lifeProposal) throws DAOException;
	
	public LifeProposal findLifeProposalByOrderId(String orderId) throws DAOException;


}
