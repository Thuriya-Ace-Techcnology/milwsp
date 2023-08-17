/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.buildingclass.service.interfaces;

import java.util.List;

import org.ace.insurance.system.fire.buildingclass.BuildingClass;

public interface IBuildingClassService {
	public BuildingClass findBuildingClassById(String id);

	public List<BuildingClass> findAllBuildingClass();

	public BuildingClass findBuildingClassByRoofWallFloor(String roof, String wall, String floor);

}
