package org.ace.insurance.travel.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.TableName;
import org.ace.insurance.travel.MobileTravelInsuredPerson;
import org.ace.insurance.travel.MobileTravelProposal;
import org.ace.insurance.travel.persistence.interfaces.IMobileTravelProposalDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("MobileTravelProposalDAO")
public class MobileTravelProposalDAO extends BasicDAO implements IMobileTravelProposalDAO {

	private void insertProcessLog(MobileTravelProposal travelProposal) {
		insertProcessLog(TableName.MOBILE_TRAVEL_PROPOSAL, travelProposal.getId());
		for (MobileTravelInsuredPerson e : travelProposal.getInsuredPersonList()) {
			insertProcessLog(TableName.MOBILE_TRAVEL_INSURED_PERSON, e.getId());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(MobileTravelProposal travelProposal) throws DAOException {
		try {
			em.persist(travelProposal);
			insertProcessLog(travelProposal);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert MobileTravelProposal", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(MobileTravelProposal mTravelProposal) throws DAOException {
		try {
			em.merge(mTravelProposal);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileTravelProposal by TransactionID", pe);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal findById(String id) throws DAOException {
		MobileTravelProposal result = null;
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.findById");
			q.setParameter("id", id);
			result = (MobileTravelProposal) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileTravelProposal by mobileUser", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileTravelProposal> findByMobileUserWithRowCount(String mobileUserId, Integer rowCount)
			throws DAOException {
		List<MobileTravelProposal> resultList = null;
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.findByMobileUser");
			q.setParameter("mobileUserId", mobileUserId);
			if (rowCount != null && rowCount > 0) {
				q.setMaxResults(rowCount);
			}
			resultList = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileTravelProposal by mobileUser", pe);
		}
		return resultList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal findMobileTravelProposalByTransactionCode(String uniqueTransactionCode)
			throws DAOException {
		MobileTravelProposal result = null;
		try {
			Query q = em.createQuery(
					"SELECT mtp FROM MobileTravelProposal mtp WHERE mtp.transactionId = :uniqueTransactionCode");
			q.setParameter("uniqueTransactionCode", uniqueTransactionCode);
			result = (MobileTravelProposal) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ProductID by TransactionID", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileTravelProposal> findByOrderId(String orderId) {
		List<MobileTravelProposal> result;
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.findByOrderId");
			q.setParameter("orderId", orderId);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileTravelProposal by orderId", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal updateByPaymentStatus(String orderId) {
		MobileTravelProposal result = new MobileTravelProposal();
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.updateByPaymentStatus");
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
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal updateResponseStatusByOrderId(String orderId, ResponseStatus responseStatus) {
		MobileTravelProposal result = new MobileTravelProposal();
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.responseStatusByOrderId");
			q.setParameter("responseStatus", responseStatus);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileTravelProposal", pe);
		}

		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileTravelProposal updateDeleteStatusByPaymentStatus(String orderId) {
		MobileTravelProposal result = new MobileTravelProposal();
		boolean deleteStatus = true;
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.updateDeleteStatusByPaymentStatus");
			q.setParameter("deleteStatus", deleteStatus);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileTravelProposal", pe);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileTravelProposal> findByFromToDate(String fromDate, String toDate, String convert) {
		List<MobileTravelProposal> result = null;
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.findByFromToDate");
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("fromDate", new SimpleDateFormat("yyyy-MM-dd").parse(fromDate));
			q.setParameter("toDate", new SimpleDateFormat("yyyy-MM-dd").parse(toDate));

			if (("yes").equalsIgnoreCase(convert)) {
				q.setParameter("tConvert", true);
				q.setParameter("fConvert", true);
			} else if (("no").equalsIgnoreCase(convert)) {
				q.setParameter("tConvert", false);
				q.setParameter("fConvert", false);
			} else {
				q.setParameter("tConvert", true);
				q.setParameter("fConvert", false);
			}
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileTravelProposal by orderId", pe);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<MobileTravelProposal> findRecordsByResponseStatus() {
		List<MobileTravelProposal> result = null;
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.findByResponseStatus");
			q.setParameter("responseStatus", ResponseStatus.SUCCESS);
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileTravelProposal by orderId", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateConvertedStatusByOrderId(PaymentOrderConfirm orderId) {
		try {
			Query q = em.createNamedQuery("MobileTravelProposal.updateConvertStatusByOrderId");
			q.setParameter("convert", true);
			q.setParameter("orderId", orderId.getOrderId());
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileTravelProposal", pe);
		}
	}
}
