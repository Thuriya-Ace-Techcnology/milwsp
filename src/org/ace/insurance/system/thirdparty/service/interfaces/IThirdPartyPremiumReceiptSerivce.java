package org.ace.insurance.system.thirdparty.service.interfaces;

import java.io.IOException;
/*
 * @author Kyaw Yea Lwin
 * @date 29/06/2020
 */
import java.util.List;

import org.ace.insurance.system.productTypeRecords.ProductTypeRecords;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirmDTO;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumReceiptDTO;
import org.ace.ws.model.thirdParty.ThirdPartyPremiumRecordsDTO;
import org.apache.http.client.ClientProtocolException;

public interface IThirdPartyPremiumReceiptSerivce {

	public ThirdPartyPremiumReceipt insert(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException;

	public ThirdPartyPremiumReceipt update(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException;

	public void delete(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException;

	public ThirdPartyPremiumReceipt findRecordsByOrderId(String orderId) throws DAOException;

	public ThirdPartyPremiumReceipt findById(String id) throws DAOException;

	public ThirdPartyPremiumReceipt findByVehicleNo(String vehicle_no) throws DAOException;

	public List<ThirdPartyPremiumReceipt> findReceiptListByVehicleNo(String vehicle_no) throws DAOException;

	public List<ThirdPartyPremiumReceipt> findAllThirdPartyPremiumReceipt() throws DAOException;

	public boolean checkPremiumReceipt(ThirdPartyPremiumReceipt receipt);

	public ThirdPartyPremiumReceiptDTO calculatePremiumReceipt(ThirdPartyPremiumReceipt receipt);

	public List<ThirdPartyPremiumRecordsDTO> findByFromToDate(String fromDate, String toDate, String branch,
			String convert);

	public List<ThirdPartyPremiumRecordsDTO> findByFromToDateForReport(String fromDate, String toDate, String branch,
			String convert);

	public List<ThirdPartyPremiumReceipt> findByDateAndVehicleNo(String fromDate, String toDate, String vehicle_no);

	public ThirdPartyPremiumReceipt updateByPaymentStatus(String orderId);

	public void calltoOthersServerAPIThirdPartyPremiumReceipt(ThirdPartyPremiumReceiptDTO thirdPartyPremiumReceiptDTO,
			ProductTypeRecords productTypeRecords) throws ClientProtocolException, IOException;

	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status);

	public List<ThirdPartyPremiumReceipt> findRecordsByResponseStatus();

	public void batchProcess() throws ClientProtocolException, IOException;

	public void postDataSyncProcess(String order_id);

	public void updateCovertedStatusByOderId(PaymentOrderConfirmDTO paymentOrderConfirm);
}
