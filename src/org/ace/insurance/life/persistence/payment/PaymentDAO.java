package org.ace.insurance.life.persistence.payment;


import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.life.dao.entities.Payment;
import org.ace.insurance.life.dao.entities.TLF;
import org.ace.insurance.life.persistence.payment.interfaces.IPaymentDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("PaymentDAO")
public class PaymentDAO extends BasicDAO implements IPaymentDAO {
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Payment insert(Payment payment) throws DAOException {
		try {
			em.persist(payment);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert Payment", pe);
		}
		return payment;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Payment update(Payment payment) throws DAOException {
		try {
			payment = em.merge(payment);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert Payment", pe);
		}
		return payment;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String findCheckOfAccountNameByCode(String acName, String branchId, String currencyId) throws DAOException {
		String result = null;
		try {
			StringBuffer str = new StringBuffer();
			str.append("SELECT a.ccoaId FROM COASetup a WHERE a.acName = :acName AND a.currencyId = :currencyId AND a.branchId = :branchId");
			Query query = em.createQuery(str.toString());
			query.setParameter("acName", acName);
			query.setParameter("currencyId", currencyId);
			query.setParameter("branchId", branchId);
			result = (String) query.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert TLF", pe);
		}
		return result;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public String findCCOAByCode(String acCode, String branchId, String currencyId) {
		String result = null;
		try {
			StringBuffer str = new StringBuffer();
			str.append("SELECT ccoa.id FROM CCOA ccoa JOIN Coa coa ON coa.id = ccoa.coaId ");
			str.append("WHERE coa.acCode = :acCode AND ccoa.currencyId = :currencyId AND ccoa.branchId = :branchId");
			Query query = em.createQuery(str.toString());
			query.setParameter("acCode", acCode);
			query.setParameter("currencyId", currencyId);
			query.setParameter("branchId", branchId);
			result = (String) query.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ccoaid BY acCode,currencyId,branchId", pe);
		}
		return result;
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertTLFList(List<TLF> tlfList) throws DAOException {
		try {
			for (TLF tlf : tlfList) {
				em.persist(tlf);
				em.flush();
			}
		} catch (PersistenceException pe) {
			throw translate("Failed to insert TLF", pe);
		}
	}


}
