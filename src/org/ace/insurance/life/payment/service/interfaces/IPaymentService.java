package org.ace.insurance.life.payment.service.interfaces;

import org.ace.insurance.life.dao.entities.Payment;
import org.ace.java.component.SystemException;

public interface IPaymentService {
	
	
	public void activatePayment(Payment payment, String policyNo, double rate) throws SystemException;

}
