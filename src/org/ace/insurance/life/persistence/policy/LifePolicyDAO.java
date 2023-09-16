package org.ace.insurance.life.persistence.policy;

import java.util.List;

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
	
	/* Find Seaman Policy By CDC No */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<LifePolicy> findSeamanPolicyByCdcNo(String cdcNo, String productId) throws DAOException {
		List<LifePolicy> result = null;
		try {
			StringBuffer query = new StringBuffer();
			query.append("SELECT lp FROM LifePolicy lp JOIN lp.policyInsuredPersonList pip JOIN pip.product prod WHERE pip.cdcNo = :cdcNo ");
			if (productId != null) {
				query.append("AND prod.id = :productId");
			}
			Query q = em.createQuery(query.toString());
			q.setParameter("cdcNo", cdcNo.trim());
			if (productId != null) {
				q.setParameter("productId", productId);
			}
			result = q.getResultList();
			em.flush();
		
		} catch (PersistenceException pe) {
			throw translate("Failed to find Seaman Policy by cdcNo: " + cdcNo, pe);
		}
		return result;
	}
	
	
	/* Find with Policy Id  to print certificate */
	@Transactional(propagation = Propagation.REQUIRED)
	public LifePolicy findSeamanPolicyByPolicyId(String id) throws DAOException  {
		LifePolicy result = new LifePolicy();
		try {
			Query q = em.createNamedQuery("LifePolicy.findByPolicyId");
			q.setParameter("lifePolicyId", id.trim());
			result = (LifePolicy) q.getSingleResult();
			em.flush();
		}catch (NoResultException e) {
			return null;
		}catch (PersistenceException pe) {
			throw translate("Failed to find Seaman Policy by id: " + id, pe);
		}
		return result;
	}


}
