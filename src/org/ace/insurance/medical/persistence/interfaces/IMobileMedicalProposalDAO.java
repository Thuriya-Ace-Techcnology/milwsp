package org.ace.insurance.medical.persistence.interfaces;

import java.util.List;

import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.ace.ws.model.mobileMedicalproposal.InsuredPersonDTO;

public interface IMobileMedicalProposalDAO {
	
	public void insert(MobileMedicalProposal medicalProposal) throws DAOException;

	public List<MobileMedicalProposal> findByOrderId(String orderId);

	public MobileMedicalProposal updateDeleteStuatusByPaymentStatus(String orderId);

	public List<InsuredPersonDTO> findByMobileUser(String mobileUserId) throws DAOException;

	public void update(MobileMedicalProposal mobileMedicalProposal) throws DAOException;

	public MobileMedicalProposal findHealthProposalByRefNo(String refNo) throws DAOException;

	public MobileMedicalProposal updateByPaymentStatus(String orderId);

	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status);

	public List<MobileMedicalProposal> findRecordsByResponseStatus();

	public List<MobileMedicalProposal> findByFromToDate(String fromDate, String toDate, String convert);

	public void updateConvertedStatusByOrderId(PaymentOrderConfirm orderId);

}
