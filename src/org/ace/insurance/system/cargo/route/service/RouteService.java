/***************************************************************************************
 * @author <<Chan Mrate Ko Ko>>
 * @Date 2015-08-05
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.cargo.route.service;

import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.system.cargo.route.Route;
import org.ace.insurance.system.cargo.route.persistence.interfaces.IRouteDAO;
import org.ace.insurance.system.cargo.route.service.interfaces.IRouteService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("RouteService")
public class RouteService extends BaseService implements IRouteService {

	@Resource(name = "RouteDAO")
	private IRouteDAO routeDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Route> findAllRoute() {
		List<Route> result = null;
		try {
			result = routeDAO.findAll();
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find all route", e);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Route findRouteById(String id) {
		Route route = null;
		try {
			route = routeDAO.findById(id);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find a Company (ID : " + id + ")", e);
		}
		return route;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Route> findByCriteria(String criteria) {
		List<Route> result = null;
		try {
			result = routeDAO.findByCriteria(criteria);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find Route by criteria " + criteria, e);
		}
		return result;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Route> findRouteByInsuranceType(String insuranceType) {
		List<Route> route = null;
		try {
			route = routeDAO.findByInsuranceType(insuranceType);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Faield to find Route (Insurnace Type : " + insuranceType + ")", e);
		}
		return route;
	}
}
