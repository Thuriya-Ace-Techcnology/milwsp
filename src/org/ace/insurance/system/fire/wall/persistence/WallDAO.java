/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.wall.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.fire.wall.Wall;
import org.ace.insurance.system.fire.wall.persistence.interfaces.IWallDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("WallDAO")
public class WallDAO extends BasicDAO implements IWallDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public Wall findById(String id) throws DAOException {
		Wall result = null;
		try {
			result = em.find(Wall.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Wall", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Wall> findAll() throws DAOException {
		List<Wall> result = null;
		try {
			Query q = em.createNamedQuery("Wall.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Wall", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Wall> findByCriteria(String criteria) throws DAOException {
		List<Wall> result = null;
		try {
			Query q = em.createQuery("Select w from Wall w where w.name Like '" + criteria + "%'");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of Wall.", pe);
		}
		return result;
	}
}
