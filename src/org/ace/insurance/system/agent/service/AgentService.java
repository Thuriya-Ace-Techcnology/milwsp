package org.ace.insurance.system.agent.service;

import java.util.Optional;

import javax.annotation.Resource;

import org.ace.insurance.agent.Agent;
import org.ace.insurance.common.OutboundAgentInfo;
import org.ace.insurance.system.agent.persistence.interfaces.IAgentDAO;
import org.ace.insurance.system.agent.service.interfaces.IAgentService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("AgentService")
public class AgentService extends BaseService implements IAgentService {


	@Resource(name = "AgentDAO")
	private IAgentDAO agentDAO;
	
	@Override
	public OutboundAgentInfo findAgentByLiscenceNoAndDateOfBirth(String liscenceNo, String dob) {
		Agent agent = agentDAO
				.findAgentByLiscenceNoAndDateOfBirth(liscenceNo, dob);
		return agent == null? null : new OutboundAgentInfo(liscenceNo,dob,agent.getId(),agent.getFullName());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<Agent> findById(String id) {
		Optional<Agent> result;
		try {
			result=Optional.ofNullable(agentDAO.findById(id));
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(),
					"Failed to find a Agent ID  : " + id + ")", e);
		}
		return result;
	}	
	
		
}
