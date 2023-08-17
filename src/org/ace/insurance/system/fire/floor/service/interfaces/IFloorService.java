/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.floor.service.interfaces;

import java.util.List;

import org.ace.insurance.system.fire.floor.Floor;

public interface IFloorService {

	public Floor findFloorById(String id);

	public List<Floor> findAllFloor();
}
