package org.ace.insurance.system.rta.persistence;

/*
 * @author Kyaw Yea Lwin
 * @date 14/07/2020
 */
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.rta.RTA;
import org.ace.insurance.system.rta.TempRTA;
import org.ace.insurance.system.rta.persistence.interfaces.IRTADAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("RTADAO")
public class RTADAO extends BasicDAO implements IRTADAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RTA insert(RTA rta) throws DAOException {
		try {
			em.persist(rta);
			return rta;
		} catch (PersistenceException pe) {
			throw translate("Failed to insert RTA ", pe);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TempRTA insertRTATemp(TempRTA rta) throws DAOException {
		try {
			em.persist(rta);
			return rta;
		} catch (PersistenceException pe) {
			throw translate("Failed to insert RTATemp ", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RTA update(RTA rta) throws DAOException {
		try {
			em.merge(rta);
			em.flush();
			return rta;
		} catch (PersistenceException pe) {
			throw translate("Failed to update RTA", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(RTA rta) throws DAOException {
		try {
			rta = em.merge(rta);
			em.remove(rta);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to delete RTA", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RTA findById(String id) throws DAOException {
		RTA result = null;
		try {
			result = em.find(RTA.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find RTA", pe);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<RTA> findAllRTA() throws DAOException {
		List<RTA> result = null;
		try {
			Query q = em.createQuery("SELECT t FROM RTA t ORDER BY t.name ASC");
			result = q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to findAll RTA :", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertList(List<RTA> rtaList) throws DAOException {
		for(RTA rta :rtaList) {
			if(rta != null) {
				insert(rta);
			}
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertRTATempList(List<TempRTA> rtaList) throws DAOException {
		for(TempRTA rta :rtaList) {
			if(rta != null) {
				insertRTATemp(rta);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RTA findByVehicleNo(String vehicleNo) throws DAOException {
		RTA result = new RTA();
		try {
			Query q = em.createQuery("SELECT t FROM RTA t WHERE t.reg_no = :regNo");
			q.setParameter("regNo", vehicleNo);
			result =(RTA) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to findAll RTA :", pe);
		}
		return result;
	}

}
