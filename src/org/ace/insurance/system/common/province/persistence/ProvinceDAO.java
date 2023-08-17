/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.common.province.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.common.province.Province;
import org.ace.insurance.system.common.province.persistence.interfaces.IProvinceDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("ProvinceDAO")
public class ProvinceDAO extends BasicDAO implements IProvinceDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(Province province) throws DAOException {
		try {
			em.persist(province);
			insertProcessLog(TableName.PROVINCE, province.getId());
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert Province", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Province province) throws DAOException {
		try {
			em.merge(province);
			updateProcessLog(TableName.PROVINCE, province.getId());
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update Province", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Province province) throws DAOException {
		try {
			province = em.merge(province);
			em.remove(province);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update Province", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Province findById(String id) throws DAOException {
		Province result = null;
		try {
			result = em.find(Province.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Province", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Province> findAll() throws DAOException {
		List<Province> result = null;
		try {
			Query q = em.createNamedQuery("Province.findAll");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Province", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Province> findByCriteria(String criteria) throws DAOException {
		List<Province> result = null;
		try {
			Query q = em.createQuery("Select p from Province p where p.name Like '" + criteria + "%'");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of Province.", pe);
		}
		return result;
	}
}
