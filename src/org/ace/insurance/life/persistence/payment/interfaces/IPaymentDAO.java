package org.ace.insurance.life.persistence.payment.interfaces;

import org.ace.insurance.life.dao.entities.Payment;
import org.ace.java.component.persistence.exception.DAOException;

public interface IPaymentDAO {
	
	public Payment update(Payment payment) throws DAOException;
	
	public Payment insert(Payment payment) throws DAOException;

}
