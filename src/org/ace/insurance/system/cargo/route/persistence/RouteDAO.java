/***************************************************************************************
 * @author <<Chan Mrate Ko Ko>>
 * @Date 2015-08-05
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.cargo.route.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.cargo.route.Route;
import org.ace.insurance.system.cargo.route.persistence.interfaces.IRouteDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("RouteDAO")
public class RouteDAO extends BasicDAO implements IRouteDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Route> findAll() {
		List<Route> result = null;
		try {
			Query q = em.createNamedQuery("Route.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Route.", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Route findById(String id) throws DAOException {
		Route result = null;
		try {
			result = em.find(Route.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Route(ID = " + id + ")", pe);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Route> findByCriteria(String criteria) throws DAOException {
		List<Route> result = null;
		try {
			// Query q = em.createNamedQuery("Route.findByCriteria");
			Query q = em.createQuery("Select r from Route r where r.name Like '" + criteria + "%'");
			// q.setParameter("criteriaValue", "%" + criteria + "%");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of Route.", pe);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Route> findByInsuranceType(String insuranceType) throws DAOException {
		List<Route> result = null;
		try {
			Query q = em.createQuery("Select r from Route r where  r.insuranceType like '%" + insuranceType + "%' ");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Route(Insurance Type = " + insuranceType + ")", pe);
		}
		return result;
	}

}
