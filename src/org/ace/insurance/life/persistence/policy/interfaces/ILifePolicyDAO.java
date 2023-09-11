package org.ace.insurance.life.persistence.policy.interfaces;

import org.ace.insurance.life.dao.entities.LifePolicy;
import org.ace.java.component.persistence.exception.DAOException;

public interface ILifePolicyDAO {
	
	public void insert(LifePolicy lifePolicy) throws DAOException;
	
	public LifePolicy findByProposalId(String proposalId) throws DAOException;
	
	public void update(LifePolicy lifePolicy) throws DAOException;

}
