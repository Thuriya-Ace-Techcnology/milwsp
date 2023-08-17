/***************************************************************************************
 * @author <<Your Name>>
 * @Date 2013-02-11
 * @Version 1.0
 * @Purpose <<You have to write the comment the main purpose of this class>>
 * 
 *    
 ***************************************************************************************/
package org.ace.insurance.system.common.township.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.common.province.Province;
import org.ace.insurance.system.common.township.Township;
import org.ace.insurance.system.common.township.persistence.interfaces.ITownshipDAO;
import org.ace.insurance.system.common.township.service.TownshipService;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("TownshipDAO")
public class TownshipDAO extends BasicDAO implements ITownshipDAO {

	/*
	 * @Transactional(propagation = Propagation.REQUIRED) public void
	 * insert(Township township) throws DAOException { try { em.persist(township);
	 * insertProcessLog(TableName.TOWNSHIP, township.getId()); em.flush(); } catch
	 * (PersistenceException pe) { throw translate("Failed to insert Township", pe);
	 * } }
	 */
	private static final Logger logger = Logger.getLogger(TownshipDAO.class);
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Township township) throws DAOException {
		try {
			em.merge(township);
			updateProcessLog(TableName.TOWNSHIP, township.getId());
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update Township", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Township township) throws DAOException {
		try {
			township = em.merge(township);
			em.remove(township);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update Township", pe);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Township findById(String id) throws DAOException {
		Township result = null;
		try {
			result = em.find(Township.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Township", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Township> findByProvince(Province province) throws DAOException {
		List<Township> result = null;
		try {
			StringBuffer sb = new StringBuffer("SELECT t.* FROM township t WHERE 1=1 ");
			if(province.getId() != null && province.getId() != "") {
				sb.append(" and t.DISTRICTID in (select d.ID from district d where d.PROVINCEID = '"+province.getId()+"')");
			}
			if(province.getId() == null && province.getProvinceNo() !=  null && province.getProvinceNo() != "") {
				sb.append(" and t.DISTRICTID in (select d.ID from district d where d.PROVINCEID = ");
				sb.append("(select p.id from province p where p.provinceNo = '"+province.getProvinceNo()+"'))");
			}
			logger.info("Query :"+sb.toString());
			Query q = em.createNativeQuery(sb.toString(),Township.class);
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Township", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Township> findAll() throws DAOException {
		List<Township> result = null;
		try {
			Query q = em.createNamedQuery("Township.findAll");
			result = q.getResultList();
			q.setMaxResults(50);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find all of Township", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Township> findByCriteria(String criteria) throws DAOException {
		List<Township> result = null;
		try {
			// Query q = em.createNamedQuery("Township.findByCriteria");
			Query q = em.createQuery("Select t from Township t where t.name Like '" + criteria + "%'");
			// q.setParameter("criteriaValue", "%" + criteria + "%");
			result = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find by criteria of Township.", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String findNameById(String id) throws DAOException {
		String name = null;
		try {
			Query query = em.createQuery("SELECT t.name FROM Township t where t.id = :id");
			query.setParameter("id", id);
			name = (String) query.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Township", pe);
		}
		return name;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Township insert(Township township) throws DAOException {
		try {
			em.persist(township);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert township(id = " + township.getId() + ")", pe);
		}
		return township;
	}
}
