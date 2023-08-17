package org.ace.insurance.system.paymentTransaction.service.interfaces;

import org.ace.java.component.SystemException;

public interface ITransactionsService {
	public String findPaymentStatusByMerRefNoAndDestination(String merRefNo, String destination) throws SystemException;
}
