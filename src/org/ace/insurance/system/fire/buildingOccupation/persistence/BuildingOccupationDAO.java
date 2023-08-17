package org.ace.insurance.system.fire.buildingOccupation.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.fire.buildingOccupation.BuildingOccupation;
import org.ace.insurance.system.fire.buildingOccupation.persistence.interfaces.IBuildingOccupationDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("BuildingOccupationDAO")
public class BuildingOccupationDAO extends BasicDAO implements IBuildingOccupationDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public BuildingOccupation findById(String id) throws DAOException {
		BuildingOccupation result = null;
		try {
			result = em.find(BuildingOccupation.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find BuildingOccupation", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<BuildingOccupation> findByInsuranceType(InsuranceType insuranceType) throws DAOException {
		List<BuildingOccupation> result = null;
		try {
			Query q = em.createNamedQuery("BuildingOccupation.findByInsuranceType");
			q.setParameter("insuranceType", insuranceType);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of BuildingOccupation by Insurance Type", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<BuildingOccupation> findAll() throws DAOException {
		List<BuildingOccupation> result = null;
		try {
			Query q = em.createNamedQuery("BuildingOccupation.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of BuildingOccupation", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<BuildingOccupation> findByCriteria(String criteria) throws DAOException {
		List<BuildingOccupation> result = null;
		try {
			// Query q =
			// em.createNamedQuery("BuildingOccupation.findByCriteria");
			Query q = em.createQuery("Select t from BuildingOccupation t where t.name Like '" + criteria + "%'");
			// q.setParameter("criteriaValue", "%" + criteria + "%");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of BuildingOccupation.", pe);
		}
		return result;
	}

}
