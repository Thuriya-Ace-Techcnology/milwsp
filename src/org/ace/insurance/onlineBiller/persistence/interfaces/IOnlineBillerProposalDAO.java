package org.ace.insurance.onlineBiller.persistence.interfaces;

import java.util.Date;
import java.util.List;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.onlineBiller.OnlineBillerBuyer;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.onlineBiller.OnlineBiller;

public interface IOnlineBillerProposalDAO {

	public void insert(OnlineBiller onlineBiller) throws DAOException;
	
	OnlineBiller findOnlineBillerByInvoiceNo(String invoiceNo) throws DAOException;

	void addNewOnlineBillerProduct(OnlineBillerBuyer buyer);

	int updateTempOnlineBillerStatus(String invoiceNo);

	OnlineBillerBuyer updateByPaymentStatus(String orderId);

	public OnlineBillerBuyer findOnlineBillerByOrderId(String orderId);

	public List<OnlineBillerBuyer> findOnlineBillerByPaymentStatus();

	public void updateResponseStatusByOrderId(String orderId);
	
	public List<OnlineBillerBuyer> findOnlineBillerByDate(Date fromDate,Date toDate,BuyerPlatForm buyPlatForm) throws DAOException;
}

