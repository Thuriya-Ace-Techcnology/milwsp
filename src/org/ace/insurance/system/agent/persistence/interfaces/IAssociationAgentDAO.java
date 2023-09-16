package org.ace.insurance.system.agent.persistence.interfaces;

import java.util.List;

import org.ace.insurance.agent.OutboundAssociationAgent;
import org.ace.java.component.persistence.exception.DAOException;

public interface IAssociationAgentDAO {
    void insert(OutboundAssociationAgent associationAgent) throws DAOException;
    void update(OutboundAssociationAgent associationAgent) throws DAOException;
    void delete(OutboundAssociationAgent associationAgent) throws DAOException;
    List<OutboundAssociationAgent> findAll() throws DAOException;
    OutboundAssociationAgent findById(String id) throws DAOException;
    public boolean checkExistingAgent(String licenceNo) throws DAOException;
	OutboundAssociationAgent checkAuthorizeAgent(String name, String password)throws DAOException;

}
