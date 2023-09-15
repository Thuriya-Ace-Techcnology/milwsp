package org.ace.insurance.life.persistence.policy;

import javax.persistence.NoResultException;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.life.dao.entities.LifePolicy;
import org.ace.insurance.life.persistence.policy.interfaces.ILifePolicyDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("LifePolicyDAO")
public class LifePolicyDAO extends BasicDAO implements ILifePolicyDAO{

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(LifePolicy lifePolicy) throws DAOException {
		try {
			em.persist(lifePolicy);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("failed to insert LifePolicy", pe);
		}
		
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public LifePolicy findByProposalId(String proposalId) throws DAOException {
		LifePolicy result = null;
		try {
			Query q = em.createNamedQuery("LifePolicy.findByProposalId");
			q.setParameter("lifeProposalId", proposalId);
			result = (LifePolicy) q.getSingleResult();
			em.flush();
		} catch (NoResultException e) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of LifePolicy by ProposalID : " + proposalId, pe);
		}
		return result;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(LifePolicy lifePolicy) throws DAOException {
		try {
			em.merge(lifePolicy);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("failed to update LifePolicy", pe);
		}

	}




}
