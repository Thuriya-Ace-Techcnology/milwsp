/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.wall.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.fire.wall.Wall;
import org.ace.java.component.persistence.exception.DAOException;

public interface IWallDAO {
	public Wall findById(String id) throws DAOException;

	public List<Wall> findAll() throws DAOException;

	public List<Wall> findByCriteria(String criteria) throws DAOException;
}
