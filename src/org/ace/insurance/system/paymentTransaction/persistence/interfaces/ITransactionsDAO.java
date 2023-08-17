package org.ace.insurance.system.paymentTransaction.persistence.interfaces;

import org.ace.java.component.persistence.exception.DAOException;

public interface ITransactionsDAO {
	public String findStatusByMerRefNoAndDestination(String merRefNo, String destination) throws DAOException;
}
