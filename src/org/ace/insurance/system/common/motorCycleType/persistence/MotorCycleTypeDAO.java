package org.ace.insurance.system.common.motorCycleType.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.common.motorCycleType.MotorCycleType;
import org.ace.insurance.system.common.motorCycleType.persistence.interfaces.IMotorCycleTypeDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("MotorCycleTypeDAO")
public class MotorCycleTypeDAO extends BasicDAO implements IMotorCycleTypeDAO{
	@Transactional(propagation = Propagation.REQUIRED)
	public MotorCycleType insert(MotorCycleType motorCycleType) throws DAOException {
		try {
			em.persist(motorCycleType);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert MotorCycleType(id = " + motorCycleType.getId() + ")", pe);
		}
		return motorCycleType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MotorCycleType update(MotorCycleType motorCycleType) throws DAOException {
		try {
			motorCycleType = em.merge(motorCycleType);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MotorCycleType(id = " + motorCycleType.getId() + ")", pe);
		}
		return motorCycleType;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MotorCycleType motorCycleType) throws DAOException {
		try {
			motorCycleType = em.merge(motorCycleType);
			em.remove(motorCycleType);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to delete MotorCycleType(id = " + motorCycleType.getId() + ")", pe);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MotorCycleType> findAll() {
		List<MotorCycleType> result = null;
		try {
			Query q = em.createNamedQuery("MotorCycleType.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of MotorCycleType.", pe);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MotorCycleType> findByInsuranceType(InsuranceType insuranceType) {
		List<MotorCycleType> result = null;
		try {
			Query q = em.createQuery("SELECT c FROM MotorCycleType c WHERE c.insuranceType = :insuranceType");
			q.setParameter("insuranceType", insuranceType);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of MotorCycleType By Insurance Type", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MotorCycleType findById(String id) throws DAOException {
		MotorCycleType result = null;
		try {
			result = em.find(MotorCycleType.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MotorCycleType(ID = " + id + ")", pe);
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MotorCycleType> findByCriteria(String criteria) throws DAOException {
		List<MotorCycleType> result = null;
		try {
			Query q = em.createQuery("Select t from MotorCycleType t where t.name Like '" + criteria + "%'");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of MotorCycleType.", pe);
		}
		return result;
	}
}
