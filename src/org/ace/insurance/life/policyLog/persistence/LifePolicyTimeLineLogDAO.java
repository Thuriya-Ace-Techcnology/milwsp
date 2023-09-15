package org.ace.insurance.life.policyLog.persistence;


import javax.persistence.PersistenceException;
import org.ace.insurance.life.dao.entities.LifePolicyIdLog;
import org.ace.insurance.life.dao.entities.LifePolicyTimeLineLog;
import org.ace.insurance.life.policyLog.persistence.interfaces.ILifePolicyTimeLineLogDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("LifePolicyTimeLineLogDAO")
public class LifePolicyTimeLineLogDAO extends BasicDAO implements ILifePolicyTimeLineLogDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public void addLifePolicyIdLog(LifePolicyIdLog lifePolicyIdLog) throws DAOException {
		try {
			em.persist(lifePolicyIdLog);
		} catch (PersistenceException pe) {
			throw translate("failed to insert LifePolicyIdLog", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void addLifePolicyTimeLineLog(LifePolicyTimeLineLog lifePolicyTimeLineLog) throws DAOException {
		try {
			em.persist(lifePolicyTimeLineLog);
		} catch (PersistenceException pe) {
			throw translate("failed to insert LifePolicyTimeLineLog", pe);
		}
	}

}
