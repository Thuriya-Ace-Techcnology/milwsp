package org.ace.insurance.system.agent.persistence;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.agent.OutboundAssociationAgent;
import org.ace.insurance.system.agent.persistence.interfaces.IAssociationAgentDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("AssociationAgentDAO")
public class AssociationAgentDAO extends BasicDAO implements IAssociationAgentDAO{

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(OutboundAssociationAgent associationAgent) throws DAOException {
		try {
            em.persist(associationAgent);
            em.flush();
        } catch (PersistenceException pe) {
            throw translate("Failed to insert outbound association agent", pe);
        }
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(OutboundAssociationAgent associationAgent) throws DAOException {
		 try {
	            em.merge(associationAgent);
	            em.flush();
	        } catch (PersistenceException pe) {
	            throw translate("Failed to update outbound association agent", pe);
	        }
		
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(OutboundAssociationAgent associationAgent) throws DAOException {
        try {
            associationAgent = em.merge(associationAgent);
            em.remove(associationAgent);
            em.flush();
        } catch (PersistenceException pe) {
            throw translate("Failed to delete outbound association agent ", pe);
        }
		
	}

	public List<OutboundAssociationAgent> findAll() throws DAOException {
        try {
            Query q = em.createNamedQuery("OutboundAssociationAgent.findAll");
            return  q.getResultList();
        } catch (PersistenceException pe) {
            throw translate("Failed to find all outbound association agent ", pe);
        }
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public OutboundAssociationAgent findById(String id) throws DAOException {
		OutboundAssociationAgent result = null;
        try {
            result = em.find(OutboundAssociationAgent.class, id);
            em.flush();
        } catch (PersistenceException pe) {
            throw translate("Failed to find outbound association agent", pe);
        }
        return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean checkExistingAgent(String licenceNo) throws DAOException {
		boolean result = false;
		try {
			Query q = em.createQuery(" SELECT a From OutboundAssociationAgent a WHERE a.licenceNo = :licenceNo");
			q.setParameter("licenceNo", licenceNo);
			OutboundAssociationAgent associationAgent = (OutboundAssociationAgent) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return true;
		} catch (PersistenceException pe) {
			throw translate("Failed to find Association Agent", pe);
		}
		return result;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public OutboundAssociationAgent checkAuthorizeAgent(String name, String password) throws DAOException {
		OutboundAssociationAgent result = null;
        try {
        	Query q = em.createNamedQuery("OutboundAssociationAgent.checkAuthorizeAgent");
			q.setParameter("name", name.trim());
			q.setParameter("password", password);
			result = (OutboundAssociationAgent) q.getSingleResult();
			em.flush();
        }  catch (NoResultException e) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find Agent: " + name, pe);
		}
		return result;
	}


}
