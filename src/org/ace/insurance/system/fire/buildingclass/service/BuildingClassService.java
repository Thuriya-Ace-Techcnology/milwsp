/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.buildingclass.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.buildingclass.BuildingClass;
import org.ace.insurance.system.fire.buildingclass.persistence.interfaces.IBuildingClassDAO;
import org.ace.insurance.system.fire.buildingclass.service.interfaces.IBuildingClassService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;

@Service(value = "BuildingClassService")
public class BuildingClassService extends BaseService implements IBuildingClassService {

	@Resource(name = "BuildingClassDAO")
	private IBuildingClassDAO buildingClassDAO;

	public List<BuildingClass> findAllBuildingClass() {
		List<BuildingClass> result = null;
		try {
			result = buildingClassDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all of BuildingClass)", e);
		}
		return result;
	}

	public BuildingClass findBuildingClassById(String id) {
		BuildingClass result = null;
		try {
			result = buildingClassDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a BuildingClass (ID : " + id + ")", e);
		}
		return result;
	}

	public BuildingClass findBuildingClassByRoofWallFloor(String roof, String wall, String floor) {
		BuildingClass result = null;
		try {
			result = buildingClassDAO.findByRoofWallFloor(roof, wall, floor);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a BuildingClass by Roof,Wall,Floor ", e);
		}
		return result;
	}
}