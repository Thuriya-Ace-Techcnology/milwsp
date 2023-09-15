package org.ace.insurance.life.payment.service.interfaces;


import java.util.List;

import org.ace.insurance.life.dao.entities.AgentCommission;
import org.ace.insurance.life.dao.entities.Payment;
import org.ace.insurance.life.dao.entities.TlfData;
import org.ace.insurance.life.enums.PolicyReferenceType;
import org.ace.insurance.life.interfaces.IPolicy;
import org.ace.java.component.SystemException;

public interface ITlfDataProcessor { 
	public TlfData getInstance(PolicyReferenceType referenceType, IPolicy policy, Payment payment, List<AgentCommission> agentCommissionList, boolean isRenewal)
			throws SystemException;
}
