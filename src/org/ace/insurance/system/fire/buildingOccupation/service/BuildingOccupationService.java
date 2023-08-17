package org.ace.insurance.system.fire.buildingOccupation.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.fire.buildingOccupation.BuildingOccupation;
import org.ace.insurance.system.fire.buildingOccupation.persistence.interfaces.IBuildingOccupationDAO;
import org.ace.insurance.system.fire.buildingOccupation.service.interfaces.IBuildingOccupationService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "BuildingOccupationService")
public class BuildingOccupationService extends BaseService implements IBuildingOccupationService {

	@Resource(name = "BuildingOccupationDAO")
	private IBuildingOccupationDAO buildingOccupationDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public List<BuildingOccupation> findAllBuildingOccupation() {
		List<BuildingOccupation> result = null;
		try {
			result = buildingOccupationDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all of BuildingOccupation)", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public BuildingOccupation findBuildingOccupationById(String id) {
		BuildingOccupation result = null;
		try {
			result = buildingOccupationDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a BuildingOccupation (ID : " + id + ")", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<BuildingOccupation> findBuildingOccupationByInsuranceType(InsuranceType insuranceType) {
		List<BuildingOccupation> result = null;
		try {
			result = buildingOccupationDAO.findByInsuranceType(insuranceType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a BuildingOccupation by Insurance Type ", e);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<BuildingOccupation> findByCriteria(String criteria) {
		List<BuildingOccupation> result = null;
		try {
			result = buildingOccupationDAO.findByCriteria(criteria);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find BuildingOccupation by criteria " + criteria, e);
		}
		return result;
	}

}