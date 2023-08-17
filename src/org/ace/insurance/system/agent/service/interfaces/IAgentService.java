package org.ace.insurance.system.agent.service.interfaces;



import java.util.Optional;

import org.ace.insurance.agent.Agent;
import org.ace.insurance.common.OutboundAgentInfo;

public interface IAgentService {
	public OutboundAgentInfo findAgentByLiscenceNoAndDateOfBirth(String liscenceNo,String dob);
	
	public Optional<Agent> findById(String id);
	
	
}
