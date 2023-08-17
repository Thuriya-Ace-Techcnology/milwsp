/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.common.country.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.common.country.Country;
import org.ace.insurance.system.common.country.persistence.interfaces.ICountryDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("CountryDAO")
public class CountryDAO extends BasicDAO implements ICountryDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(Country country) throws DAOException {
		try {
			em.persist(country);
			insertProcessLog(TableName.COUNTRY, country.getId());
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert Country", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Country country) throws DAOException {
		try {
			em.merge(country);
			updateProcessLog(TableName.COUNTRY, country.getId());
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update Country", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Country country) throws DAOException {
		try {
			country = em.merge(country);
			em.remove(country);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update Country", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Country findById(String id) throws DAOException {
		Country result = null;
		try {
			result = em.find(Country.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Country", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Country> findAll() throws DAOException {
		List<Country> result = null;
		try {
			Query q = em.createNamedQuery("Country.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Country", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Country> findByCriteria(String criteria) throws DAOException {
		List<Country> result = null;
		try {
			// Query q = em.createNamedQuery("Country.findByCriteria");
			Query q = em.createQuery("Select t from Country t where t.name Like '" + criteria + "%'");
			// q.setParameter("criteriaValue", "%" + criteria + "%");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of Country.", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String findNameById(String id) throws DAOException {
		String name = null;
		try {
			Query query = em.createQuery("SELECT c.name FROM Country c where c.id = :id");
			query.setParameter("id", id);
			name = (String) query.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Township", pe);
		}
		return name;
	}
}
