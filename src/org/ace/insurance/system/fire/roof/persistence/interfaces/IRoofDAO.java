/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.fire.roof.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.fire.roof.Roof;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRoofDAO {

	public Roof findById(String id) throws DAOException;

	public List<Roof> findAll() throws DAOException;
}
