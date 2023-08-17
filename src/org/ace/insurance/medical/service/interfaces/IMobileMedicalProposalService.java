package org.ace.insurance.medical.service.interfaces;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.mobileMedicalproposal.InsuredPersonDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalDTO;
import org.ace.ws.model.mobileMedicalproposal.MedicalProposalInsuredPersonDTO;
import org.apache.http.client.ClientProtocolException;

public interface IMobileMedicalProposalService {
	public MobileMedicalProposal addNewMedicalProposal(MobileMedicalProposal mobileMedicalProposal);

	public MobileMedicalProposal updateNewMedicalProposal(MobileMedicalProposal mobileMedicalProposal);

	public List<InsuredPersonDTO> findByMobileUser(String mobileUserId);

	public List<MobileMedicalProposal> findByOrderId(String orderId);

	public void updatePremuimAmount(MobileMedicalProposal mobileMedicalProposal, List<MedicalProposalInsuredPersonDTO> insuredPersonList);

	public MobileMedicalProposal findProposalByRefNo(String referenceNo);

	public MobileMedicalProposal updateByPaymentStatus(String order_id);
	
	public void calltoOthersServerAPIForMP(MedicalProposalDTO medicalProposalDTO, ProductTypeRecords productTypeRecords) throws ClientProtocolException, IOException;

	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status);

	public List<MobileMedicalProposal> findRecordsByResponseStatus();

	public void postDataSyncProcess(String order_id) throws UnsupportedEncodingException;

	public List<MedicalProposalDTO> fndByFromToDate(String fromDate, String toDate, String convert);

	public void updateCovertedStatusByOderId(PaymentOrderConfirmDTO paymentOrderConfirm);	
	

}
