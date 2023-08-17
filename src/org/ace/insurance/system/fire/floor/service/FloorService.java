/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.floor.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.fire.floor.Floor;
import org.ace.insurance.system.fire.floor.persistence.interfaces.IFloorDAO;
import org.ace.insurance.system.fire.floor.service.interfaces.IFloorService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;

@Service(value = "FloorService")
public class FloorService extends BaseService implements IFloorService {

	@Resource(name = "FloorDAO")
	private IFloorDAO floorDAO;

	public List<Floor> findAllFloor() {
		List<Floor> result = null;
		
		try {
			//result = floorDAO.findAll();
			result = getFixedFloorList();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all of Floor)", e);
		}
		
		return result;
	}

	public Floor findFloorById(String id) {
		Floor result = null;
		try {
			result = floorDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a Floor (ID : " + id + ")", e);
		}
		return result;
	}
	
	private List<Floor> getFixedFloorList(){
		List<Floor> floorList = new ArrayList<>();
		Floor floor = new Floor("ISSYS0280001000000000131032013","CONCRETE");
		floorList.add(floor);
		floor = new Floor("ISSYS0280001000000000231032013","GROUND");
		floorList.add(floor);
		floor = new Floor("ISSYS0280001000000000331032013","TIMBER");
		floorList.add(floor);
		
		return floorList;
	}
}