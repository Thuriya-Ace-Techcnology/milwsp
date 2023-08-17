package org.ace.insurance.system.fire.paeRate.persistence;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.fire.paeRate.persistence.interfaces.IPaeRateDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("PaeRateDAO")
public class PaeRateDAO extends BasicDAO implements IPaeRateDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public double findRateByClassAndAge(String buildingClassId, int buildingAge) throws DAOException {
		double result = 0.0;
		try {
			Query q = em.createQuery("SELECT p.paeRate FROM PAEBuildingClass p WHERE p.buildingClass.id = :buildingClassId AND p.fromAge <= :buildingAge AND p.toAge >= :buildingAge  ");
			q.setParameter("buildingClassId", buildingClassId);
			q.setParameter("buildingAge", buildingAge);
			result = Double.parseDouble(q.getSingleResult().toString());
			em.flush();
		} catch (NoResultException pe) {
			return result;
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of PAEBuildingClass", pe);
		}
		return result;
	}

}
