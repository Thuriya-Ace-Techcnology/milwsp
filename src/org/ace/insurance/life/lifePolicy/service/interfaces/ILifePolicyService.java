package org.ace.insurance.life.lifePolicy.service.interfaces;

import java.util.List;

import org.ace.insurance.life.dao.entities.LifePolicy;
import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.insurance.life.dto.LifePolicyDTO;
import org.ace.java.component.SystemException;

public interface ILifePolicyService {
	
	public void addNewLifePolicy(LifePolicy lifePolicy) throws SystemException;
	
	public LifePolicy activateLifePolicy(LifeProposal lifeProposal);

	public List<LifePolicyDTO> findSeamanPolicyByCDCNo(String cdcNo, String productId);

	public LifePolicyDTO findByPolicyId(String policyId);

}
