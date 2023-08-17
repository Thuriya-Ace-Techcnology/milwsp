package org.ace.insurance.system.agent.persistence.interfaces;


import org.ace.insurance.agent.Agent;

public interface IAgentDAO {
	public Agent findAgentByLiscenceNoAndDateOfBirth(String liscenceNo,String dob);
	
	public Agent findById(String id);
	
}
