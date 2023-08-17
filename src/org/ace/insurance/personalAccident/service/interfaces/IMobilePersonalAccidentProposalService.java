package org.ace.insurance.personalAccident.service.interfaces;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.ace.insurance.personalAccident.MobilePersonalAccidentProposal;
import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.mobilePersonalAccidentproposal.MPAP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAIP001;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAInsuredPerson001;
import org.ace.ws.model.transaction.TwoC2P001;
import org.apache.http.client.ClientProtocolException;

public interface IMobilePersonalAccidentProposalService {
	public MobilePersonalAccidentProposal addNewPAProposal(MobilePersonalAccidentProposal mobilePaProposal);

	public MobilePersonalAccidentProposal updateNewPAProposal(MobilePersonalAccidentProposal mobilePaProposal);

	// public MobilePersonalAccidentProposal
	// deletePAProposal(MobilePersonalAccidentProposal mobilePaProposal);

	public void updatePAProposal(TwoC2P001 TwoC2P001);

	public List<PAInsuredPerson001> findByMobileUserWithRowCount(String mobileUserId, Integer rowCount);

	public MobilePersonalAccidentProposal findProposalByRefNo(String refNo);

	public List<MobilePersonalAccidentProposal> findByOrderId(String orderId);

	public MobilePersonalAccidentProposal updateByPaymentStatus(String orderId);

	public void updatePremuimAmount(MobilePersonalAccidentProposal mobilePersonalAccidentProposal, List<PAIP001> insuredPersonList);

	public List<MPAP001> fndByFromToDate(String fromDate, String toDate,String convert);

	public void calltoOthersServerAPI(MPAP001 mpap001, ProductTypeRecords productTypeRecords) throws ClientProtocolException, IOException;

	public void updateResponseStatusByOrderId(String orderId, ResponseStatus requestNotFound);

	public List<MobilePersonalAccidentProposal> findRecordsByResponseStatus();

	public void postDataSyncProcess(String order_id)  throws UnsupportedEncodingException ;

	public void updateCovertedStatusByOderId(PaymentOrderConfirmDTO paymentOrderConfirm);

}
