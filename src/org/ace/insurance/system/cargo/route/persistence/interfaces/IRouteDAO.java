/***************************************************************************************
 * @author <<Chan Mrate Ko Ko>>
 * @Date 2015-08-05
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.cargo.route.persistence.interfaces;

import java.util.List;

import org.ace.insurance.system.cargo.route.Route;
import org.ace.java.component.persistence.exception.DAOException;

public interface IRouteDAO {

	public List<Route> findAll() throws DAOException;

	public Route findById(String id) throws DAOException;

	public List<Route> findByCriteria(String criteria) throws DAOException;
	
	public List<Route> findByInsuranceType(String insuranceType) throws DAOException;

}
