package org.ace.insurance.life.persistence.payment;


import javax.persistence.PersistenceException;

import org.ace.insurance.life.dao.entities.Payment;
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


}
