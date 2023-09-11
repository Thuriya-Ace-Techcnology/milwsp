package org.ace.insurance.life.persistence.proposal;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.insurance.life.persistence.proposal.interfaces.ILifeProposalDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("LifeProposalDAO")
public class LifeProposalDAO extends BasicDAO implements ILifeProposalDAO{

    @Transactional(propagation = Propagation.REQUIRED)
    public LifeProposal insert(LifeProposal lifeProposal) throws DAOException {
        try {
            em.persist(lifeProposal);
            em.flush();
        } catch (PersistenceException pe) {
            throw translate("Failed to insert LifeProposal", pe);
        }
        return lifeProposal;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(LifeProposal lifeProposal) throws DAOException {
        try {
            em.merge(lifeProposal);
            em.flush();
        } catch (PersistenceException pe) {
            throw translate("Failed to update LifeProposal", pe);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(LifeProposal lifeProposal) throws DAOException {
        try {
            lifeProposal = em.merge(lifeProposal);
            em.remove(lifeProposal);
            em.flush();
        } catch (PersistenceException pe) {
            throw translate("Failed to update LifeProposal", pe);
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
	public LifeProposal findLifeProposalByOrderId(String orderId) throws DAOException {
    	LifeProposal result = null;
    	try {
            Query q = em.createNamedQuery("LifeProposal.findByOrderId");
            q.setParameter("orderId", orderId);
           
            result = (LifeProposal) q.getSingleResult();
            em.flush();
    	} catch (PersistenceException pe) {
    		throw translate("Failed to find LifeProposal",pe);
    	}
		
		return result;
	}

	


}
