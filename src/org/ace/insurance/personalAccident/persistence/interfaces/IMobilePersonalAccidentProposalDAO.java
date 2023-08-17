package org.ace.insurance.personalAccident.persistence.interfaces;

import java.util.List;

import org.ace.insurance.personalAccident.MobilePersonalAccidentProposal;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAInsuredPerson001;

public interface IMobilePersonalAccidentProposalDAO {
	public void insert(MobilePersonalAccidentProposal paProposal) throws DAOException;

	public List<PAInsuredPerson001> findByMobileUserWithRowCount(String mobileUserId, Integer rowCount) throws DAOException;

	public MobilePersonalAccidentProposal findById(String id) throws DAOException;

	public void update(MobilePersonalAccidentProposal mPAProposal) throws DAOException;

	// public void delete(MobilePersonalAccidentProposal mPAProposal) throws
	// DAOException;

	public MobilePersonalAccidentProposal findMobilePAProposalByTransactionCode(String uniqueTransactionCode);

	public MobilePersonalAccidentProposal findPAProposalByRefNo(String refNo) throws DAOException;

	public List<MobilePersonalAccidentProposal> findByOrderId(String orderId);

	public MobilePersonalAccidentProposal updateByPaymentStatus(String orderId);

	public MobilePersonalAccidentProposal updateDeleteStuatusByPaymentStatus(String orderId);

	public List<MobilePersonalAccidentProposal> findByFromToDate(String fromDate, String toDate,String convert);

	public List<MobilePersonalAccidentProposal> findRecordsByResponseStatus();

	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status);

	public void updateConvertedStatusByOrderId(PaymentOrderConfirm orderId);
}
