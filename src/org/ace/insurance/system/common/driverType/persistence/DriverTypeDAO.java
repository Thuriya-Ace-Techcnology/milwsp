package org.ace.insurance.system.common.driverType.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.common.driverType.DriverType;
import org.ace.insurance.system.common.driverType.persistence.interfaces.IDriverTypeDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("DriverTypeDAO")
public class DriverTypeDAO extends BasicDAO implements IDriverTypeDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public DriverType insert(DriverType driverType) throws DAOException {
		try {
			em.persist(driverType);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert DriverType(id = " + driverType.getId() + ")", pe);
		}
		return driverType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public DriverType update(DriverType driverType) throws DAOException {
		try {
			driverType = em.merge(driverType);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update DriverType(id = " + driverType.getId() + ")", pe);
		}
		return driverType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(DriverType driverType) throws DAOException {
		try {
			driverType = em.merge(driverType);
			em.remove(driverType);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to delete DriverType(id = " + driverType.getId() + ")", pe);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverType> findAll() {
		List<DriverType> result = null;
		try {
			Query q = em.createNamedQuery("DriverType.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of DriverType.", pe);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverType> findByInsuranceType(InsuranceType insuranceType) {
		List<DriverType> result = null;
		try {
			Query q = em.createQuery("SELECT c FROM DriverType c WHERE c.insuranceType = :insuranceType");
			q.setParameter("insuranceType", insuranceType);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of DriverType By Insurance Type", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public DriverType findById(String id) throws DAOException {
		DriverType result = null;
		try {
			result = em.find(DriverType.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find DriverType(ID = " + id + ")", pe);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<DriverType> findByCriteria(String criteria) throws DAOException {
		List<DriverType> result = null;
		try {
			Query q = em.createQuery("Select t from DriverType t where t.name Like '" + criteria + "%'");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of DriverType.", pe);
		}
		return result;
	}

}
