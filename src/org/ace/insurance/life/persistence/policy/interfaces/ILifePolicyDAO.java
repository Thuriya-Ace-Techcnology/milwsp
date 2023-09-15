package org.ace.insurance.life.persistence.policy.interfaces;

import java.util.List;

import org.ace.insurance.life.dao.entities.LifePolicy;
import org.ace.java.component.persistence.exception.DAOException;

public interface ILifePolicyDAO {
	
	public void insert(LifePolicy lifePolicy) throws DAOException;
	
	public LifePolicy findByProposalId(String proposalId) throws DAOException;
	
	public void update(LifePolicy lifePolicy) throws DAOException;

	public List<LifePolicy> findSeamanPolicyByCdcNo(String cdcNo, String productId);

	public LifePolicy findSeamanPolicyByPolicyId(String id);

}
