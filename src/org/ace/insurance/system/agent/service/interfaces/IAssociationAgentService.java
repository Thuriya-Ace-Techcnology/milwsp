package org.ace.insurance.system.agent.service.interfaces;

import java.util.List;

import org.ace.insurance.agent.OutboundAssociationAgent;
import org.ace.java.component.persistence.exception.DAOException;

public interface IAssociationAgentService {
    void addNewAssociationAgent(OutboundAssociationAgent associationAgent);
    void updateAssociationAgent(OutboundAssociationAgent associationAgent);
    void deleteAssociationAgent(OutboundAssociationAgent associationAgent);
    OutboundAssociationAgent findById(String id) throws DAOException;
	List<OutboundAssociationAgent> getAssociationAgentList();
	public boolean checkExistingAgent(String licenceNo);

}
