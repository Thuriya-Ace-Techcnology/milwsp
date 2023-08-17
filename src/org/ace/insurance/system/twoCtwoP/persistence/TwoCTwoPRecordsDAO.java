package org.ace.insurance.system.twoCtwoP.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.TableName;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.insurance.system.twoCtwoP.TwoCTwoPRecords;
import org.ace.insurance.system.twoCtwoP.persistence.interfaes.ITwoCTwoPRecordsDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("TwoCTwoPRecordsDAO")
public class TwoCTwoPRecordsDAO extends BasicDAO implements ITwoCTwoPRecordsDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TwoCTwoPRecords insert(TwoCTwoPRecords twoC2PRecords) throws DAOException {
		try {
			em.persist(twoC2PRecords);
			insertProcessLog(TableName.TWOCTWOP_RECORDS, twoC2PRecords.getId());
			return twoC2PRecords;
		} catch (PersistenceException pe) {
			throw translate("Failed to insert TwoCTwoPRecords", pe);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TwoCTwoPRecords update(TwoCTwoPRecords twoC2PRecords) throws DAOException {
		try {
			em.merge(twoC2PRecords);
			em.flush();
			return twoC2PRecords;
		} catch (PersistenceException pe) {
			throw translate("Failed to update TwoCTwoPRecords", pe);
		}
	}

	@Override
	public void delete(TwoCTwoPRecords twoC2PRecords) throws DAOException {
		try {
			twoC2PRecords = em.merge(twoC2PRecords);
			em.remove(twoC2PRecords);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to delete TwoCTwoPRecords", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TwoCTwoPRecords findById(String id) throws DAOException {
		TwoCTwoPRecords result = null;
		try {
			result = em.find(TwoCTwoPRecords.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find TwoCTwoPRecords", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<TwoCTwoPRecords> findAllTwoCTwoPRecords() throws DAOException {
		List<TwoCTwoPRecords> result = null;
		try {
			Query q = em.createQuery("SELECT t FROM TwoCTwoPRecords t ORDER BY t.order_id ASC");
			result = q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to findAll TwoCTwoPRecords :", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TwoCTwoPRecords findByOrderId(String orderId) throws DAOException {
		TwoCTwoPRecords result = null;
		try {
			 Query q = em.createNamedQuery("TwoCTwoPRecords.findByOrderId");
			 q.setParameter("orderId", orderId);
			result = (TwoCTwoPRecords) q.getResultList().get(0);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find TwoCTwoPRecords", pe);
		}
		return result;
	}
}
