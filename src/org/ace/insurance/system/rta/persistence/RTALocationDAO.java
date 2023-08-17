package org.ace.insurance.system.rta.persistence;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.rta.RTALocation;
import org.ace.insurance.system.rta.persistence.interfaces.IRTALocationDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("RTALocationDAO")
public class RTALocationDAO extends BasicDAO implements IRTALocationDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RTALocation findByCode(String code) throws DAOException {
		RTALocation result = null;
		try {
			Query query = em.createNamedQuery("RTALocation.findByCode");
			query.setParameter("code", code);
			result = (RTALocation) query.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		}
		return result;
	}

}
