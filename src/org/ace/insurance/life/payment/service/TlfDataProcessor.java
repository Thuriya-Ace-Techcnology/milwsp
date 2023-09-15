package org.ace.insurance.life.payment.service;

import java.util.ArrayList;

import java.util.List;

import org.ace.insurance.agent.COACode;
import org.ace.insurance.life.dao.entities.AgentCommission;
import org.ace.insurance.life.dao.entities.Payment;
import org.ace.insurance.life.dao.entities.TlfData;
import org.ace.insurance.life.enums.PaymentChannel;
import org.ace.insurance.life.enums.PolicyReferenceType;
import org.ace.insurance.life.interfaces.IPolicy;
import org.ace.insurance.life.payment.service.interfaces.ITlfDataProcessor;
import org.ace.java.component.SystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "TlfDataProcessor")
public class TlfDataProcessor implements ITlfDataProcessor {
	

	public TlfData getInstance(PolicyReferenceType type, IPolicy policy, Payment payment, List<AgentCommission> agentCommissionList, boolean isRenewal) throws SystemException {
		TlfData tlfData = null;
		try {
			boolean isAgent = policy.getAgent() != null ? true : false;
			double sumInsured = policy.getTotalSumInsured();

			/* load Data */
			tlfData = loadTlfDataAccCode(type, payment.getPaymentChannel(), isAgent);
			String customerId = policy.getCustomerId();
			String customerName = policy.getInsuredPersonName();
			tlfData.setSumInsured(sumInsured);
			tlfData.setCustomerId(customerId);
			tlfData.setCustomerName(customerName);
			tlfData.setPayment(payment);
			tlfData.setReceivableDr("CASH");
			tlfData.setSalePointName("HEAD OFFICE");
			tlfData.setAgentCommissionList(agentCommissionList);
			// tlfData.setCoinsu001(coinsu001);
			tlfData.setRenewal(isRenewal);
			tlfData.setUnit(policy.getTotalUnit());
		} catch (Exception e) {
			throw new SystemException(e.getMessage(), "Failed to add a new TLF", e);
		}

		return tlfData;
	}
	
	
	
	private TlfData loadTlfDataAccCode(PolicyReferenceType type, PaymentChannel channel, boolean isAgent) {
		TlfData tlfData = null;
		switch (type) {


			case SEAMANONLINE_POLICY:
				tlfData = loadSeamanOnlineTlfDataAccCode(channel, isAgent);
				break;


				
			default:
				break;
		}

		return tlfData;
	}
	
	
	private TlfData loadSeamanOnlineTlfDataAccCode(PaymentChannel param, boolean isAgent) {
		TlfData tlfData = new TlfData();
		/* Premium */
		String premiumCodeDr = PaymentChannel.CASHED.equals(param) ? COACode.CASH : PaymentChannel.CHEQUE.equals(param) ? COACode.SEAMAN_ONLINE_RECEIVABLE : null;
		String premiumCodeCr = COACode.SEAMAN_ONLINE_PREMIUM;
		/* Cheque */
		String chequeCodeCr = PaymentChannel.CHEQUE.equals(param) ? COACode.SEAMAN_ONLINE_RECEIVABLE : null;
		/* Service Charges */
		String servicesCodeDr = PaymentChannel.CASHED.equals(param) ? COACode.CASH : PaymentChannel.CHEQUE.equals(param) ? COACode.SEAMAN_ONLINE_RECEIVABLE : null;
		String servicesCodeCr = COACode.SEAMAN_ONLINE_SERVICE_CHARGES;
		tlfData.setPremiumCodeDr(premiumCodeDr);
		tlfData.setPremiumCodeCr(premiumCodeCr);
		tlfData.setChequeCodeCr(chequeCodeCr);
		tlfData.setServicesCodeDr(servicesCodeDr);
		tlfData.setServicesCodeCr(servicesCodeCr);

		return tlfData;
	}
	
	
	

}
