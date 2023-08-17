package org.ace.insurance.system.cargo.cargoType.persistence;

/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.cargo.cargoType.CargoType;
import org.ace.insurance.system.cargo.cargoType.persistence.interfaces.ICargoTypeDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("CargoTypeDAO")
public class CargoTypeDAO extends BasicDAO implements ICargoTypeDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CargoType> findAll() {
		List<CargoType> result = null;
		try {
			Query q = em.createNamedQuery("CargoType.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of CargoType.", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public CargoType findById(String id) throws DAOException {
		CargoType result = null;
		try {
			result = em.find(CargoType.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find CargoType(ID = " + id + ")", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CargoType> findByCriteria(String criteria) throws DAOException {
		List<CargoType> result = null;
		try {
			Query q = em.createQuery("Select t from CargoType t where t.name Like '" + criteria + "%'");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of CargoType.", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<CargoType> findByInsuranceType(String insuranceType) throws DAOException {
		List<CargoType> result = null;
		try {
			Query q = em.createQuery("Select t from CargoType t where t.insuranceType Like '" + insuranceType + "%'");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by insuranceType of CargoType.", pe);
		}
		return result;
	}
}
