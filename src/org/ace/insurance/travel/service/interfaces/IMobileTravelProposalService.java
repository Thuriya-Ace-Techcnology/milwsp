package org.ace.insurance.travel.service.interfaces;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.travel.MobileTravelProposal;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.mobiletravelproposal.MIP001;
import org.ace.ws.model.mobiletravelproposal.MTP001;
import org.ace.ws.model.transaction.TwoC2P001;
import org.apache.http.client.ClientProtocolException;

public interface IMobileTravelProposalService {
	public MobileTravelProposal addNewTravelProposal(MobileTravelProposal mobileTravelProposal);

	public MobileTravelProposal addCoreNewTravelProposal(MobileTravelProposal mobileTravelProposal);
	
	public void updateTravelProposal(TwoC2P001 TwoC2P001);

	public MobileTravelProposal updateNewTravleProposal(MobileTravelProposal mobileTravelProposal);

	public List<MobileTravelProposal> findByMobileUserWithRowCount(String mobileUserId, Integer rowCount);

	public List<MobileTravelProposal> findByOrderId(String orderId);

	public MobileTravelProposal findMobileTravelProposalByTransactionCode(String uniqueTransactionCode);

	public MobileTravelProposal updateByPaymentStatus(String orderId);

	// public MobileTravelProposal updateDeleteStatusByPaymentStatus(String
	// orderId);
	public MobileTravelProposal updateResponseStatusByOrderId(String orderId,ResponseStatus responseStatus);
	
	public void updatePremuimAmount(MobileTravelProposal mobileTravelProposal, List<MIP001> insuredPersonList);

	public List<MTP001> findByFromToDate(String fromDate, String toDate,String convert);

	public void calltoOthersServerAPI(MTP001 mtp001, ProductTypeRecords productTypeRecords) throws ClientProtocolException, IOException;

	public List<MobileTravelProposal> findRecordsByResponseStatus();

	public void postDataSyncProcess(String order_id) throws UnsupportedEncodingException;

	public void updateCovertedStatusByOderId(PaymentOrderConfirmDTO paymentOrderConfirm);
}
