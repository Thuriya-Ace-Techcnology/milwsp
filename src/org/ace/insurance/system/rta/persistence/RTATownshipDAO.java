package org.ace.insurance.system.rta.persistence;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.rta.RTATownship;
import org.ace.insurance.system.rta.persistence.interfaces.IRTATownshipDAO;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("RTATownshipDAO")
public class RTATownshipDAO extends BasicDAO implements IRTATownshipDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RTATownship findByCode(String code) throws DAOException {
		RTATownship result = null;
		try {
			Query q = em.createNamedQuery("RTATownship.findByCode");
			q.setParameter("code", code);
			result = (RTATownship) q.getSingleResult();
			if(result == null) {
				return result;
			}
			em.flush();
		} catch (NoResultException pe) {
			return result;
		} catch (PersistenceException pe) {
			throw translate("Failed to find RTATownship", pe);
		}
		
		return result;
	}
}
