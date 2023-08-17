package org.ace.insurance.system.common.occupation.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.common.occupation.Occupation;
import org.ace.insurance.system.common.occupation.persistence.interfaces.IOccupationDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("OccupationDAO")
public class OccupationDAO extends BasicDAO implements IOccupationDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Occupation> findAll() throws DAOException {
		List<Occupation> result = null;
		try {
			Query q = em.createNamedQuery("Occupation.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Occupation", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Occupation findById(String id) throws DAOException {

		Occupation result = null;
		try {
			result = em.find(Occupation.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Occupation", pe);
		}
		return result;
	}

}
