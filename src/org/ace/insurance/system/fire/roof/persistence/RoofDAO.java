/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.roof.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.fire.roof.Roof;
import org.ace.insurance.system.fire.roof.persistence.interfaces.IRoofDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("RoofDAO")
public class RoofDAO extends BasicDAO implements IRoofDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public Roof findById(String id) throws DAOException {
		Roof result = null;
		try {
			result = em.find(Roof.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Roof", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Roof> findAll() throws DAOException {
		List<Roof> result = null;
		try {
			Query q = em.createNamedQuery("Roof.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Roof", pe);
		}
		return result;
	}
}
