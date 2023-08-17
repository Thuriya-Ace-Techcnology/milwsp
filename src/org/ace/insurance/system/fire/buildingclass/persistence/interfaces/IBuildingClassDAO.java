/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.buildingclass.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.fire.buildingclass.BuildingClass;
import org.ace.java.component.persistence.exception.DAOException;

public interface IBuildingClassDAO {
	public BuildingClass findById(String id) throws DAOException;

	public List<BuildingClass> findAll() throws DAOException;

	public BuildingClass findByRoofWallFloor(String roof, String wall, String floor) throws DAOException;

}
