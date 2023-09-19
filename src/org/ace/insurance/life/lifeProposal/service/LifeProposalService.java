package org.ace.insurance.life.lifeProposal.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.ace.insurance.common.ErrorCode;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.ProposalType;
import org.ace.insurance.common.SystemConstants;
import org.ace.insurance.common.bank.service.interfaces.IBankService;
import org.ace.insurance.life.KeyFactorChecker;
import org.ace.insurance.life.dao.entities.AgentCommission;
import org.ace.insurance.life.dao.entities.LifePolicy;
import org.ace.insurance.life.dao.entities.LifePolicyIdLog;
import org.ace.insurance.life.dao.entities.LifePolicyTimeLineLog;
import org.ace.insurance.life.dao.entities.LifeProposal;
import org.ace.insurance.life.dao.entities.Payment;
import org.ace.insurance.life.dao.entities.TlfData;
import org.ace.insurance.life.enums.PaymentChannel;
import org.ace.insurance.life.enums.PolicyReferenceType;
import org.ace.insurance.life.lifePolicy.service.interfaces.ILifePolicyService;
import org.ace.insurance.life.lifeProposal.service.interfaces.ILifeProposalService;
import org.ace.insurance.life.payment.service.PaymentService;
import org.ace.insurance.life.payment.service.interfaces.IPaymentService;
import org.ace.insurance.life.payment.service.interfaces.ITlfDataProcessor;
import org.ace.insurance.life.payment.service.interfaces.ITlfProcessor;
import org.ace.insurance.life.persistence.payment.interfaces.IPaymentDAO;
import org.ace.insurance.life.persistence.proposal.interfaces.ILifeProposalDAO;
import org.ace.insurance.life.policyLog.service.interfaces.ILifePolicyTimeLineLogService;
import org.ace.insurance.product.Product;
import org.ace.insurance.system.common.branch.Branch;
import org.ace.insurance.system.common.branch.service.interfaces.IBranchService;
import org.ace.java.component.SystemException;
import org.ace.java.component.idgen.exception.CustomIDGeneratorException;
import org.ace.java.component.idgen.service.interfaces.ICustomIDGenerator;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "LifeProposalService")
public class LifeProposalService implements ILifeProposalService{
	
	@Resource(name = "LifeProposalDAO")
	private ILifeProposalDAO lifeProposalDAO;
	
	@Resource(name = "LifePolicyService")
	private ILifePolicyService lifePolicyService;
	
	@Resource(name="CustomIDGenerator")	
	private ICustomIDGenerator customIDGenerator;
	
	@Resource(name="TlfDataProcessor")	
	private ITlfDataProcessor tlfDataProcessor;
	
	@Resource(name="TlfProcessor")	
	private ITlfProcessor tlfProcessor;
	
	
	@Resource(name="PaymentDAO")	
	private IPaymentDAO paymentDAO;
	
	@Resource(name="PaymentService")
	private IPaymentService paymentService;
	
	@Resource(name="BankService")
	private IBankService bankService;
	
	
	@Resource(name="BranchService")
	private IBranchService branchService;
	
	@Resource(name="LifePolicyTimeLineLogService")
	private ILifePolicyTimeLineLogService lifePolicyTimeLineLogService;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public LifeProposal addLifeProposal(LifeProposal lifeProposal) {
		try {			
			lifeProposal.setCurrencyRate(1.0);
			lifeProposal.setProposalType(ProposalType.UNDERWRITING);
			if(KeyFactorChecker.isSeamanOnline(lifeProposal.getProposalInsuredPersonList().get(0).getProduct().getId())) {
				lifeProposal.setPeriodMonth(12);
			}			
			setProposalNo(lifeProposal);	
			lifeProposal.setProposalStatus(ProposalStatus.PENDING);
			lifeProposal = lifeProposalDAO.insert(lifeProposal);
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a new LifeProposal", e);
		} catch (CustomIDGeneratorException e) {
			throw new SystemException(e.getErrorCode(), "Failed to add a new LifeProposal", e);
		}
		return lifeProposal;
	}
	
	
	private void setProposalNo(LifeProposal lifeProposal) {
		String proposalNo = null;
		String proposalIdGen = null;
		String productId = lifeProposal.getProposalInsuredPersonList().get(0).getProduct().getId().trim();
		proposalIdGen = SystemConstants.LIFE_PROPOSAL_NO;		
		proposalNo = customIDGenerator.getCustomNextId(proposalIdGen, productId);		
		lifeProposal.setProposalNo(proposalNo);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void paymentLifeProposal(LifeProposal lifeProposal) {
		try {

			Product product = lifeProposal.getProposalInsuredPersonList().get(0).getProduct();
			boolean isSeamanOnline = KeyFactorChecker.isSeamanOnline(product.getId());

			PolicyReferenceType referenceType = isSeamanOnline ? PolicyReferenceType.SEAMANONLINE_POLICY : PolicyReferenceType.SEAMANONLINE_POLICY ;

			LifePolicy lifePolicy = new LifePolicy(lifeProposal);
			lifePolicy.setProposalStatus(ProposalStatus.UNDERWRITING);
			lifePolicyService.addNewLifePolicy(lifePolicy);
			lifePolicy = lifePolicyService.activateLifePolicy(lifeProposal);
			lifeProposal.setProposalStatus(ProposalStatus.ISSUED);
			lifeProposal.setComplete(true);
			lifeProposalDAO.update(lifeProposal);
			String invoiceNo = customIDGenerator.getCustomNextId(SystemConstants.LIFE_INVOICE_NO,
					product.getId().trim());
			Payment payment = new Payment();
			payment.setPaymentType(lifePolicy.getPaymentType());
			payment.setInvoiceNo(invoiceNo);
			payment.setBank(bankService.findBankById("ISSYS010001000000002107082019"));
			payment.setChequeNo("123456");
			payment.setPaymentChannel(PaymentChannel.CHEQUE);
			payment.setReferenceType(referenceType);
			payment.setConfirmDate(new Date());
			payment.setAccountBank(bankService.findBankById("ISSYS010001000000003727082019"));
			payment.setBasicPremium(lifePolicy.getPremium());
			payment.setCurrency(product.getCurrency());
			payment.setRate(1);
			payment.setAmount(lifePolicy.getPremium());
			payment.setFromTerm(1);
			payment.setToTerm(1);
			payment.setReferenceNo(lifePolicy.getId());			
			payment = paymentDAO.insert(payment);
			paymentService.activatePayment(payment, lifePolicy.getPolicyNo(), 1);
			List<TlfData> dataList = new ArrayList<>();
			List<AgentCommission> agentCommissionList = new ArrayList<>();
			TlfData tlfData = null;
			/* Load Account Code */
			tlfData = tlfDataProcessor.getInstance(referenceType, lifePolicy, payment, agentCommissionList, false);
			dataList.add(tlfData);
			Branch userBranch = branchService.findBranchById(lifePolicy.getBranch().getId());		
			tlfProcessor.handleTlfProcess(dataList, userBranch);
			
			/* Entry to LifePolicyTimeLineLog (Underwriting) */
			if (lifeProposal.getProposalType().equals(ProposalType.UNDERWRITING)) {
				LifePolicyTimeLineLog timeLineLog = new LifePolicyTimeLineLog(lifePolicy.getPolicyNo(),
						lifePolicy.getActivedPolicyStartDate(), lifePolicy.getActivedPolicyEndDate(),
						lifePolicy.getCommenmanceDate());
				lifePolicyTimeLineLogService.addLifePolicyTimeLineLog(timeLineLog);

				LifePolicyIdLog idLog = new LifePolicyIdLog(null, lifePolicy.getId(),
						lifePolicy.getLifeProposal().getId(), ProposalType.UNDERWRITING, timeLineLog);
				lifePolicyTimeLineLogService.addLifePolicyIdLog(idLog);
			}
			
		} catch (DAOException e) {
			throw new SystemException(e.getErrorCode(), "Failed to payment a LifeProposal", e);
		}
		
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public LifeProposal findLifeProposalByOrderId(String orderId) {		
		LifeProposal result = null;
		try {
			result = lifeProposalDAO.findLifeProposalByOrderId(orderId);			
		}catch(DAOException e) {
			throw new SystemException(e.getErrorCode(),"Failed to find LifeProposal",e);
		}
		
		return result;

	}
	
	/* Find Seaman Net Premium */
	@Transactional(propagation = Propagation.REQUIRED)
	public double getSeamanNetPremium(double premium) {
		double netPremium = 0.0;
		netPremium = premium - (premium * 0.15);
		
		return netPremium;
	}

}
