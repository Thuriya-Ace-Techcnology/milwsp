package org.ace.insurance.system.fire.buildingOccupation.persistence.interfaces;

import java.util.List;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.fire.buildingOccupation.BuildingOccupation;
import org.ace.java.component.persistence.exception.DAOException;

public interface IBuildingOccupationDAO {

	public BuildingOccupation findById(String id) throws DAOException;

	public List<BuildingOccupation> findByInsuranceType(InsuranceType insuranceType) throws DAOException;

	public List<BuildingOccupation> findAll() throws DAOException;

	public List<BuildingOccupation> findByCriteria(String criteria) throws DAOException;

}
