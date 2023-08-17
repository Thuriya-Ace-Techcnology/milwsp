package org.ace.insurance.system.fire.buildingOccupation.service.interfaces;

import java.util.List;

import org.ace.insurance.common.InsuranceType;
import org.ace.insurance.system.fire.buildingOccupation.BuildingOccupation;

public interface IBuildingOccupationService {

	public BuildingOccupation findBuildingOccupationById(String id);

	public List<BuildingOccupation> findBuildingOccupationByInsuranceType(InsuranceType insuranceType);

	public List<BuildingOccupation> findAllBuildingOccupation();

	public List<BuildingOccupation> findByCriteria(String criteria);
}
