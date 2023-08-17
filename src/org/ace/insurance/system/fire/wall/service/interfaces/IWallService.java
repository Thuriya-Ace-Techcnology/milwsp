/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.wall.service.interfaces;

import java.util.List;

import org.ace.insurance.system.fire.wall.Wall;

public interface IWallService {

	public Wall findWallById(String id);

	public List<Wall> findAllWall();

	public List<Wall> findByCriteria(String criteria);
}
