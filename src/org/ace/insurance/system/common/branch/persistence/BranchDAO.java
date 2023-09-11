/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.common.branch.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.common.branch.Branch;
import org.ace.insurance.system.common.branch.persistence.interfaces.IBranchDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("BranchDAO")
public class BranchDAO extends BasicDAO implements IBranchDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public Branch findByCode(String code) throws DAOException {
		Branch result = null;
		try {
			Query q = em.createNamedQuery("Branch.findByCode");
			q.setParameter("branchCode", code);
			result = (Branch) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Branch", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Branch> findAll() throws DAOException {
		List<Branch> result = null;
		try {
			Query q = em.createNamedQuery("Branch.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Branch", pe);
		}
		return result;
	}
	

	@Transactional(propagation = Propagation.REQUIRED)
	public Branch findById(String id) throws DAOException {
		Branch result = null;
		try {
			Query q = em.createNamedQuery("Branch.findById");
			q.setParameter("id", id);
			result = (Branch) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Branch", pe);
		}
		return result;
	}

	
	

}
