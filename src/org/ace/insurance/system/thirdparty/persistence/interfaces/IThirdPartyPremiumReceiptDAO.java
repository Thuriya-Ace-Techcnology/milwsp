package org.ace.insurance.system.thirdparty.persistence.interfaces;

/*
 * @author Kyaw Yea Lwin
 * @date 29/06/2020
 */
import java.util.List;

import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;

public interface IThirdPartyPremiumReceiptDAO {
	public ThirdPartyPremiumReceipt insert(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException;
	
	public ThirdPartyPremiumReceipt update(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException;

	public void delete(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException;

	public ThirdPartyPremiumReceipt findRecordsByOrderId(String orderId) throws DAOException;
	
	public ThirdPartyPremiumReceipt findById(String id) throws DAOException;

	public ThirdPartyPremiumReceipt findByVehicleNo(String vehicle_no) throws DAOException;
	
	public List<ThirdPartyPremiumReceipt> findReceiptListByVehicleNo(String vehicle_no) throws DAOException;

	public List<ThirdPartyPremiumReceipt> findAllThirdPartyPremiumReceipt() throws DAOException;
	
	public List<ThirdPartyPremiumReceipt> findByFromToDate(String fromDate,String toDate,String branch,String convert);
	
	public List<ThirdPartyPremiumReceipt> findByDateAndVehicleNo(String fromDate,String toDate,String vehicle_no);
	
	public ThirdPartyPremiumReceipt updateByPaymentStatus(String orderId);

	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status);

	public List<ThirdPartyPremiumReceipt> findRecordsByResponseStatus();

	public void updateConvertedStatusByOrderId(PaymentOrderConfirm orderId);
}
