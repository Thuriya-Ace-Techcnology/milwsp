package org.ace.insurance.life.payment.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.KeyFactorIDConfig;
import org.ace.insurance.life.dao.entities.AgentCommission;
import org.ace.insurance.life.dao.entities.NarrationHandler;
import org.ace.insurance.life.dao.entities.Payment;
import org.ace.insurance.life.dao.entities.TLF;
import org.ace.insurance.life.dao.entities.TlfData;
import org.ace.insurance.life.dao.entities.TlfFactory;
import org.ace.insurance.life.enums.DoubleEntry;
import org.ace.insurance.life.enums.NarrationType;
import org.ace.insurance.life.enums.PaymentChannel;
import org.ace.insurance.life.enums.PolicyReferenceType;
import org.ace.insurance.life.enums.TlfCategory;
import org.ace.insurance.life.interfaces.IPolicy;
import org.ace.insurance.life.payment.service.interfaces.ITlfDataProcessor;
import org.ace.insurance.life.payment.service.interfaces.ITlfProcessor;
import org.ace.insurance.life.persistence.payment.interfaces.IPaymentDAO;
import org.ace.insurance.system.common.branch.Branch;
import org.ace.java.component.SystemException;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.java.component.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "TlfProcessor")
public class TlfProcessor extends BaseService implements ITlfProcessor{
	
	@Resource(name="PaymentDAO")
	private IPaymentDAO paymentDAO;
	
	private final String TRCredit = KeyFactorIDConfig.getTRCredit();
	private final String TRDebit = KeyFactorIDConfig.getTRDebit();
	private final String CSCredit = KeyFactorIDConfig.getCSCredit();
	private final String CSDebit = KeyFactorIDConfig.getCSDebit();


	@Transactional(propagation = Propagation.REQUIRED)
	public void handleTlfProcess(List<TlfData> dataList, Branch branch) throws SystemException {
		try {
			List<TLF> tlfList = new ArrayList<>();
			List<TLF> tempList = null;
			String narration = null;
			NarrationHandler handler = null;
			for (TlfData data : dataList) {
				handler = new NarrationHandler(data);
				/* Premium Tlf */
				narration = handler.getInstance(NarrationType.PREMIUM);
				tempList = generatePremiumTlf(data, branch, narration);
				tlfList.addAll(tempList);

				/* Service Charges Tlf */
				if (data.getPayment().getServicesCharges() > 0) {
					narration = handler.getInstance(NarrationType.SERVICE_CHARGES);
					tempList = generateServiceChargesTlf(data, branch, narration);
					tlfList.addAll(tempList);
				}


			}

			reverseNegativeTlf(tlfList);
			paymentDAO.insertTLFList(tlfList);

		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add Tlf for premium.", e);
		}
		
	}
	
	
	
	private List<TLF> generatePremiumTlf(TlfData data, Branch branch, String narration) {
		List<TLF> result = new ArrayList<>();
		TLF tlf = null;
		try {
			PaymentChannel paymentChannel = data.getPayment().getPaymentChannel();
			String currencyId = data.getPayment().getCurrency().getId();
			String branchId = branch.getId();
			String acCode = data.getPayment().getAccountBank() != null ? data.getPayment().getAccountBank().getAcode() : "";

			/* Debit Tlf */
			String ccoaId = null;
			ccoaId = paymentDAO.findCheckOfAccountNameByCode(data.getPremiumCodeDr(), branchId, currencyId);

			TlfFactory factory = new TlfFactory(DoubleEntry.DEBIT, data, branchId, ccoaId, narration, TlfCategory.PREMIUM_TLF);
			tlf = factory.getInstance();
			result.add(tlf);

			/* Credit Tlf */
			ccoaId = paymentDAO.findCheckOfAccountNameByCode(data.getPremiumCodeCr(), branchId, currencyId);
			factory = new TlfFactory(DoubleEntry.CREDIT, data, branchId, ccoaId, narration, TlfCategory.PREMIUM_TLF);
			tlf = factory.getInstance();
			result.add(tlf);

			if (PaymentChannel.CHEQUE.equals(paymentChannel)) {
				/* Debit Tlf */
				ccoaId = paymentDAO.findCCOAByCode(acCode, branchId, currencyId);
				factory = new TlfFactory(DoubleEntry.DEBIT, data, branchId, ccoaId, narration, TlfCategory.PREMIUM_TLF);
				tlf = factory.getInstance();
				tlf.setClearing(true);
				tlf.setPayableTran(true);
				tlf.setPaid(false);
				result.add(tlf);
				/* Credit Tlf */
				ccoaId = paymentDAO.findCheckOfAccountNameByCode(data.getChequeCodeCr(), branchId, currencyId);
				factory = new TlfFactory(DoubleEntry.CREDIT, data, branchId, ccoaId, narration, TlfCategory.PREMIUM_TLF);
				tlf = factory.getInstance();
				tlf.setClearing(true);
				tlf.setPayableTran(true);
				tlf.setPaid(false);
				result.add(tlf);
			}
		} catch (DAOException exc) {
			throw new SystemException(exc.getErrorCode(), "Generate tlf for Premium.", exc);
		}
		return result;
	}
	
	
	private List<TLF> generateServiceChargesTlf(TlfData data, Branch branch, String narration) {
		List<TLF> result = new ArrayList<>();
		TLF tlf = null;
		try {

			String currencyId = data.getPayment().getCurrency().getId();
			String branchId = branch.getId();

			/* Debit Tlf */
			String ccoaId = null;
			ccoaId = paymentDAO.findCheckOfAccountNameByCode(data.getServicesCodeDr(), branchId, currencyId);
			TlfFactory factory = new TlfFactory(DoubleEntry.DEBIT, data, branchId, ccoaId, narration, TlfCategory.SERVICE_CHARGES_TLF);
			tlf = factory.getInstance();
			result.add(tlf);

			/* Credit Tlf */
			ccoaId = paymentDAO.findCheckOfAccountNameByCode(data.getServicesCodeCr(), branchId, currencyId);
			factory = new TlfFactory(DoubleEntry.CREDIT, data, branchId, ccoaId, narration, TlfCategory.SERVICE_CHARGES_TLF);
			tlf = factory.getInstance();
			result.add(tlf);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Generate tlf for service charges.", e);
		}
		return result;
	}
	
	
	private List<TLF> reverseNegativeTlf(List<TLF> tlfList) {
		String tranTypeId = null;
		for (TLF tlf : tlfList) {
			if (tlf.getLocalAmount() < 0) {
				tlf.setLocalAmount(Math.abs(tlf.getLocalAmount()));
				tlf.setHomeAmount(Math.abs(tlf.getHomeAmount()));
				tranTypeId = tlf.getTranTypeId();
				/* Reverse Tran Type **/
				if (tranTypeId.equals(CSCredit))
					tranTypeId = CSDebit;
				else if (tranTypeId.equals(CSDebit))
					tranTypeId = CSCredit;
				else if (tranTypeId.equals(TRDebit))
					tranTypeId = TRCredit;
				else if (tranTypeId.equals(TRCredit))
					tranTypeId = TRDebit;

				tlf.setTranTypeId(tranTypeId);
			}
		}
		return tlfList;
	}
	

}
