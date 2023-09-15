package org.ace.insurance.life.lifePolicy.service.interfaces;

import org.ace.insurance.life.dao.entities.LifePolicy;
import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.java.component.SystemException;

public interface ILifePolicyService {
	
	public void addNewLifePolicy(LifePolicy lifePolicy) throws SystemException;
	
	public LifePolicy activateLifePolicy(LifeProposal lifeProposal);

}
