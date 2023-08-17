package org.ace.insurance.system.paymentTransaction.service;

import javax.annotation.Resource;

import org.ace.insurance.system.paymentTransaction.persistence.interfaces.ITransactionsDAO;
import org.ace.insurance.system.paymentTransaction.service.interfaces.ITransactionsService;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "TransactionsService")
public class TransactionsService extends BaseService implements ITransactionsService {
	@Resource(name = "TransactionsDAO")
	private ITransactionsDAO transactionsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public String findPaymentStatusByMerRefNoAndDestination(String merRefNo, String destination) throws SystemException {
		String result = null;
		try {
			result = transactionsDAO.findStatusByMerRefNoAndDestination(merRefNo, destination);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to find Payment status by RefNo And Destination", e);
		}
		return result;
	}

}
