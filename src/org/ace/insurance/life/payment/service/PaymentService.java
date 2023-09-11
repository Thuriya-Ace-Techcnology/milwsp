package org.ace.insurance.life.payment.service;

import java.util.Date;

import javax.annotation.Resource;

import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.life.KeyFactorChecker;
import org.ace.insurance.life.dao.entities.Payment;
import org.ace.insurance.life.enums.PaymentChannel;
import org.ace.insurance.life.enums.PolicyReferenceType;
import org.ace.insurance.life.payment.service.interfaces.IPaymentService;
import org.ace.insurance.life.persistence.payment.interfaces.IPaymentDAO;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.service.interfaces.ICustomIDGenerator;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "PaymentService")
public class PaymentService implements IPaymentService{
	
	@Resource(name="CustomIDGenerator")	
	private ICustomIDGenerator customIDGenerator;
	
	@Resource(name="PaymentDAO")
	private IPaymentDAO paymentDAO;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void activatePayment(Payment payment, String policyNo, double rate) throws SystemException {
		try {
			PaymentChannel channel = payment.getPaymentChannel();
			PolicyReferenceType refType = payment.getReferenceType();
			String receiptNo = null;
			switch (refType) {				
				case SEAMANONLINE_POLICY:
					receiptNo = customIDGenerator.getCustomNextId(SystemConstants.SEAMAN_ONLINE_RECEIPT_NO,KeyFactorChecker.getSemanOnlineId());
					break;
				default:
					if (channel.equals(PaymentChannel.CASHED)) {
						receiptNo = customIDGenerator.getNextId(SystemConstants.CASH_RECEIPT_NO, null);
					} else if (channel.equals(PaymentChannel.CHEQUE)) {
						receiptNo = customIDGenerator.getNextId(SystemConstants.CHEQUE_RECEIPT_NO, null);
					} else if (channel.equals(PaymentChannel.TRANSFER)) {
						receiptNo = customIDGenerator.getNextId(SystemConstants.TRANSFER_RECEIPT_NO, null);
					}
					break;
			}
	
			if (channel.equals(PaymentChannel.CHEQUE))
 				payment.setPO(true);
			payment.setComplete(true);
			payment.setPaymentDate(new Date());
			if( payment.getReceiptNo()==null || payment.getReceiptNo().isEmpty()) {
				payment.setReceiptNo(receiptNo);
			}
			payment.setRate(rate);
			payment.setPolicyNo(policyNo);
			paymentDAO.update(payment);
			

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to update the actual Payment", e);
		}
	}

	
	
	

}
