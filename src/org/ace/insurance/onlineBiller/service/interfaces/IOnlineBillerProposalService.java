package org.ace.insurance.onlineBiller.service.interfaces;

import java.util.Date;
import java.util.List;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.onlineBiller.OnlineBillerBuyer;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.onlineBiller.OnlineBiller;

public interface IOnlineBillerProposalService {
    public OnlineBiller addCoreOnlineBillerProposal(OnlineBiller onlineBiller);
    
    OnlineBiller findOnlineBillerByInvoiceNo(String invoiceNo) throws DAOException;

    void addNewOnlineBillerProduct(OnlineBillerBuyer convertOnlineBillerDTOToEntity);

    int updateOnlineBillingStatus(String invoiceNo);

    OnlineBillerBuyer updateByPaymentStatus(String order_id)throws DAOException;

	public OnlineBillerBuyer findByOrderId(String orderId);

	public void batchProcess();

	void updateResponseStatusByOrderId(String orderId);
	
	public List<OnlineBillerBuyer> findOnlineBillerByDate(Date fromDate,Date toDate,BuyerPlatForm buyPlatForm) throws DAOException;
}