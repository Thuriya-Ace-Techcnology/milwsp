package org.ace.insurance.moibleCountry.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.moibleCountry.persistence.interfaces.IMobileCountryDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.MobileCountry;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("MobileCountryDAO")
public class MobileCountryDAO extends BasicDAO implements IMobileCountryDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileCountry> findAll() throws DAOException {
		List<MobileCountry> result;
		try {
			Query q = em.createNamedQuery("MobileCountry.findAll");
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileCountry ", pe);
		}
		return result;
	}

	@Override
	public String findByCountryCode(String countryCode) {
		String result = null;
		try {
			Query q = em.createNamedQuery("MobileCountry.findByCountryCode");
			q.setParameter("countryCode", countryCode);
			result = (String) q.getSingleResult();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileCountry ", pe);
		}
		return result;
	}

}
