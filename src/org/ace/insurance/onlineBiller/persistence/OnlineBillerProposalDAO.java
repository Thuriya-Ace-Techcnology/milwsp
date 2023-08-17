package org.ace.insurance.onlineBiller.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.BuyerPlatForm;
import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.onlineBiller.OnlineBillerBuyer;
import org.ace.insurance.onlineBiller.persistence.interfaces.IOnlineBillerProposalDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.onlineBiller.OnlineBiller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("OnlineBillerProposalDAO")
public class OnlineBillerProposalDAO extends BasicDAO implements IOnlineBillerProposalDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(OnlineBiller onlineBiller) throws DAOException {
		try {
			em.persist(onlineBiller);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert OnlineBillerProposal", pe);
		}
	}
	
	@Override
	public OnlineBiller findOnlineBillerByInvoiceNo(String invoiceNo) throws DAOException {
		OnlineBiller result = null;
		try {
			Query q = em.createNamedQuery("OnlineBiller.findByInvoiceNo");
			q.setParameter("findValue", invoiceNo);
			result = (OnlineBiller) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			
		}
		return result;
	}
	
	@Override
	public List<OnlineBillerBuyer> findOnlineBillerByDate(Date fromDate,Date toDate,BuyerPlatForm buyPlatForm) throws DAOException {
		List<OnlineBillerBuyer> result = null;
		try {
			Query q = em.createNamedQuery("OnlineBillerBuyer.findByDateAndPlatForm");
			q.setParameter("fromDate", fromDate);
			q.setParameter("toDate", toDate);
			q.setParameter("buyPlatForm", buyPlatForm);
			result = q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			
		}
		return result;
	}
	
	@Override
	public OnlineBillerBuyer findOnlineBillerByOrderId(String orderId) throws DAOException {
		OnlineBillerBuyer result = null;
		try {
			Query q = em.createNamedQuery("OnlineBillerBuyer.findByOrderId");
			q.setParameter("findValue", orderId);
			result = (OnlineBillerBuyer) q.getSingleResult();
			em.flush();
		} catch (NoResultException pe) {
			
		}
		return result;
	}

	@Override
	public void addNewOnlineBillerProduct(OnlineBillerBuyer buyer) {
		try {
			em.persist(buyer);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateTempOnlineBillerStatus(String invoiceNo) {
		int result  = 0;
		try {
			Query q = em.createNamedQuery("OnlineBiller.updateBuyerStatus");
			q.setParameter("findValue", invoiceNo);
			q.setParameter("boughtStatus", true);
			q.setParameter("paymentDate", new Date());
			result = q.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OnlineBillerBuyer updateByPaymentStatus(String orderId) {
		OnlineBillerBuyer result = new OnlineBillerBuyer();
		try {
			Query q = em.createNamedQuery("OnlineBillerBuyer.updateByPaymentStatus");
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("paymentDate", new Date());
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileTravelProposal", pe);
		}

		return result;
	}

	@Override
	public List<OnlineBillerBuyer> findOnlineBillerByPaymentStatus() {
		List<OnlineBillerBuyer> result = null;
		try {
			Query q = em.createNamedQuery("OnlineBillerBuyer.findByProposalStatus");
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("isCovert", false);
			result =  q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			
		}
		return result;
	}

	@Override
	public void updateResponseStatusByOrderId(String orderId) {
		try {
			Query q = em.createNamedQuery("OnlineBillerBuyer.updateConvertStatusByOrderId");
			q.setParameter("convert",true);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update ThirdPartyPremiumReceipt", pe);
		}
	}


}
