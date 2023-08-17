/***************************************************************************************
 * @author <<Chan Mrate Ko Ko>>
 * @Date 2015-08-05
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.cargo.route.service.interfaces;

import java.util.List;

import org.ace.insurance.system.cargo.route.Route;

public interface IRouteService {

	public List<Route> findAllRoute();

	public Route findRouteById(String id);

	public List<Route> findByCriteria(String criteria);
	
	public List<Route> findRouteByInsuranceType(String insuranceType);
}
