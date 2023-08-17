package org.ace.insurance.thirdPartyDriverLicense.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.thirdPartyDriverLicense.TypeOfDriver;
import org.ace.insurance.thirdPartyDriverLicense.persistence.interfaces.ITypeOfDriverDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("TypeOfDriverDAO")
public class TypeOfDriverDAO extends BasicDAO implements ITypeOfDriverDAO {

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TypeOfDriver> findAll() {
		List<TypeOfDriver> result = null;
		try {
			Query q = em.createNamedQuery("TypeOfDriver.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of TypeOfDriver.", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public TypeOfDriver findById(Long id) {
		TypeOfDriver result = null;
		try {
			Query q = em.createNamedQuery("TypeOfDriver.findById");
			q.setParameter("id", id);
			result = (TypeOfDriver) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find TypeOfDriver.", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int findYearById(Long typeOfDriverId) {
		int result;
		try {
			Query q = em.createNamedQuery("TypeOfDriver.findYearById");
			q.setParameter("id", typeOfDriverId);
			result = (int) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Year.", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public double findPremiumRateById(Long typeOfDriverId) {
		double result;
		try {
			Query q = em.createNamedQuery("TypeOfDriver.findPremiumRateById");
			q.setParameter("id", typeOfDriverId);
			result = (double) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Premium Rate.", pe);
		}
		return result;
	}

}
