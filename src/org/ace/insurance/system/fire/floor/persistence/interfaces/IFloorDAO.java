/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.floor.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.fire.floor.Floor;
import org.ace.java.component.persistence.exception.DAOException;

public interface IFloorDAO {

	public Floor findById(String id) throws DAOException;

	public List<Floor> findAll() throws DAOException;
}
