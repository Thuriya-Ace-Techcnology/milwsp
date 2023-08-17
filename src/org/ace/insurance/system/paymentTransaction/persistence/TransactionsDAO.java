package org.ace.insurance.system.paymentTransaction.persistence;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.system.paymentTransaction.persistence.interfaces.ITransactionsDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("TransactionsDAO")
public class TransactionsDAO extends BasicDAO implements ITransactionsDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public String findStatusByMerRefNoAndDestination(String merRefNo, String destination) throws DAOException {
		String result = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT t.responseCode from Transactions t WHERE t.merRefNo = :merRefNo AND t.destination = :destination");
			Query query = em.createQuery(buffer.toString());
			query.setParameter("merRefNo", merRefNo.replace("\"", ""));
			query.setParameter("destination", destination.replace("\"", ""));
			result = (String) query.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			return "";
		} catch (PersistenceException pe) {
			throw translate("Failed to find Payment Status By Reference No And destination", pe);
		}
		return result;
	}
}
