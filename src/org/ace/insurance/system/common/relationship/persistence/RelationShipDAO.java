package org.ace.insurance.system.common.relationship.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.common.relationship.RelationShip;
import org.ace.insurance.system.common.relationship.persistence.interfaces.IRelationShipDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("RelationShipDAO")
public class RelationShipDAO extends BasicDAO implements IRelationShipDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public List<RelationShip> findAll() throws DAOException {
		List<RelationShip> result = null;
		try {
			Query q = em.createNamedQuery("RelationShip.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of RelationShip", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public RelationShip findById(String id) throws DAOException {
		RelationShip result = null;
		try {
			result = em.find(RelationShip.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find RelationShip", pe);
		}
		return result;
	}

}
