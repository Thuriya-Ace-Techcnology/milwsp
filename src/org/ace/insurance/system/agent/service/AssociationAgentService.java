package org.ace.insurance.system.agent.service;
import java.util.List;


import javax.annotation.Resource;

import org.ace.insurance.agent.OutboundAssociationAgent;
import org.ace.insurance.system.agent.persistence.interfaces.IAssociationAgentDAO;
import org.ace.insurance.system.agent.service.interfaces.IAssociationAgentService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "AssociationAgentService")
public class AssociationAgentService extends BaseService implements IAssociationAgentService {
	
    @Resource(name = "AssociationAgentDAO")
    private IAssociationAgentDAO associationAgentDAO;

    @Transactional(propagation = Propagation.REQUIRED)
	public void addNewAssociationAgent(OutboundAssociationAgent associationAgent) {
        try {
            associationAgentDAO.insert(associationAgent);
        } catch (DAOException e) {
            throw new SystemException(e.getErrorCode(), "Failed to add a new outbound association agent", e);
        }
		
	}

    @Transactional(propagation = Propagation.REQUIRED)
	public void updateAssociationAgent(OutboundAssociationAgent associationAgent) {
        try {
            associationAgentDAO.update(associationAgent);
        } catch (DAOException e) {
            throw new SystemException(e.getErrorCode(), "Failed to update a outbound association agent", e);
        }
		
	}

    @Transactional(propagation = Propagation.REQUIRED)
	public void deleteAssociationAgent(OutboundAssociationAgent associationAgent) {
        try {
            associationAgentDAO.delete(associationAgent);
        } catch (DAOException e) {
            throw new SystemException(e.getErrorCode(), "Failed to delete a outbound association agent", e);
        }
		
	}

    @Transactional(propagation = Propagation.REQUIRED)
	public OutboundAssociationAgent findById(String id) throws org.ace.java.component.persistence.exception.DAOException {
        try {
            return associationAgentDAO.findById(id);
           } catch (DAOException e) {
               throw new SystemException(e.getErrorCode(), "Failed to find a outbound association agent", e);
           }
	}

    @Transactional(propagation = Propagation.REQUIRED)
	public List<OutboundAssociationAgent> getAssociationAgentList() {
		try {
			   return associationAgentDAO.findAll();		   
	    } catch (DAOException e) {
	        throw new SystemException(e.getErrorCode(), "Failed to get Office List", e);
	    }
	}
    
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean checkExistingAgent(String licenceNo) {
		boolean result = false;
		try {
			result =associationAgentDAO.checkExistingAgent(licenceNo);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find Association Agent", e);
		}
		return result;
	}
	
}