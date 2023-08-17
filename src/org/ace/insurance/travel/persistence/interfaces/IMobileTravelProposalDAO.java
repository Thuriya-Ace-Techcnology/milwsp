package org.ace.insurance.travel.persistence.interfaces;

import java.util.List;

import org.ace.insurance.travel.MobileTravelProposal;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;

public interface IMobileTravelProposalDAO {
	public void insert(MobileTravelProposal travelProposal) throws DAOException;

	public List<MobileTravelProposal> findByMobileUserWithRowCount(String mobileUserId, Integer rowCount) throws DAOException;

	public MobileTravelProposal findById(String id) throws DAOException;

	public void update(MobileTravelProposal mTravelProposal) throws DAOException;

	public MobileTravelProposal findMobileTravelProposalByTransactionCode(String uniqueTransactionCode);

	public List<MobileTravelProposal> findByOrderId(String orderId);

	public MobileTravelProposal updateByPaymentStatus(String orderId);

	public MobileTravelProposal updateResponseStatusByOrderId(String orderId,ResponseStatus responseStatus);
	
	public MobileTravelProposal updateDeleteStatusByPaymentStatus(String orderId);

	public List<MobileTravelProposal> findByFromToDate(String fromDate, String toDate,String convert);
	
	public List<MobileTravelProposal> findRecordsByResponseStatus();

	public void updateConvertedStatusByOrderId(PaymentOrderConfirm orderId);

}
