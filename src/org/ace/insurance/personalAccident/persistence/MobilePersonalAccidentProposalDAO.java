package org.ace.insurance.personalAccident.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.common.TableName;
import org.ace.insurance.personalAccident.MobilePersonalAccidentInsuredPerson;
import org.ace.insurance.personalAccident.MobilePersonalAccidentProposal;
import org.ace.insurance.personalAccident.persistence.interfaces.IMobilePersonalAccidentProposalDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.ace.ws.model.mobilePersonalAccidentproposal.PAInsuredPerson001;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("MobilePersonalAccidentProposalDAO")
public class MobilePersonalAccidentProposalDAO extends BasicDAO implements IMobilePersonalAccidentProposalDAO {

	private void insertProcessLog(MobilePersonalAccidentProposal paProposal) {
		insertProcessLog(TableName.MOBILE_PERSONALACCIDENT_PROPOSAL, paProposal.getId());
		for (MobilePersonalAccidentInsuredPerson e : paProposal.getInsuredPersonList()) {
			insertProcessLog(TableName.MOBILE_PERSONALACCIDENT_INSURED_PERSON, e.getId());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(MobilePersonalAccidentProposal paProposal) throws DAOException {
		try {
			em.persist(paProposal);
			insertProcessLog(paProposal);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert MobilePAProposal", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(MobilePersonalAccidentProposal mPAProposal) throws DAOException {
		try {
			em.merge(mPAProposal);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobilePAProposal by TransactionID", pe);
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobilePersonalAccidentProposal findById(String id) throws DAOException {
		MobilePersonalAccidentProposal result = null;
		try {
			Query q = em.createNamedQuery("MobilePersonalAccidentProposal.findById");
			q.setParameter("id", id);
			result = (MobilePersonalAccidentProposal) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobilePersonalAccidentProposal by mobileUser", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PAInsuredPerson001> findByMobileUserWithRowCount(String mobileUserId, Integer rowCount) throws DAOException {
		List<PAInsuredPerson001> resultList = null;
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT NEW " + PAInsuredPerson001.class.getName());
			buffer.append(
					"( pa.initialId,pa.firstName,pa.middleName,pa.lastName, pa.sumInsured,pa.premium,addOn.premium,mtp.proposalStatus,mtp.proposalNo,mtp.policyNo,mtp.currencyId,mtp.submittedDate,mtp.orderId,mtp.transactionFees) ");
			buffer.append("FROM MobilePersonalAccidentProposal mtp ");
			buffer.append("LEFT OUTER JOIN mtp.insuredPersonList pa ");
			buffer.append("LEFT OUTER JOIN pa.insuredPersonAddOnList addOn ");
			buffer.append("WHERE mtp.userId= :mobileUserId and mtp.deleteStatus = 0 ");
			buffer.append("ORDER BY mtp.submittedDate DESC ");
			Query q = em.createQuery(buffer.toString());

			if (rowCount != null && rowCount > 0) {
				q.setMaxResults(rowCount);
			}
			q.setParameter("mobileUserId", mobileUserId);
			resultList = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobilePersonalAccidentInsuredPerson by mobileUser", pe);
		}
		return resultList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobilePersonalAccidentProposal findMobilePAProposalByTransactionCode(String uniqueTransactionCode) throws DAOException {
		MobilePersonalAccidentProposal result = null;
		try {
			Query q = em.createQuery("SELECT mtp FROM MobilePersonalAccidentProposal mtp WHERE mtp.transactionId = :uniqueTransactionCode ");
			q.setParameter("uniqueTransactionCode", uniqueTransactionCode);
			result = (MobilePersonalAccidentProposal) q.getSingleResult();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ProductID by TransactionID", pe);
		}
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MobilePersonalAccidentProposal findPAProposalByRefNo(String refNo) throws DAOException {
		MobilePersonalAccidentProposal result = null;
		try {
			Query q = em.createQuery("SELECT mtp FROM MobilePersonalAccidentProposal mtp WHERE mtp.proposalNo = :refNo OR mtp.policyNo=:refNo and mtp.deleteStatus = 0");
			q.setParameter("refNo", refNo);
			result = (MobilePersonalAccidentProposal) q.getSingleResult();
			em.flush();
		} catch (NoResultException e) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find PA Proposal By RefNo", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobilePersonalAccidentProposal> findByOrderId(String orderId) {
		List<MobilePersonalAccidentProposal> result;
		try {
			Query q = em.createNamedQuery("MobilePersonalAccidentProposal.findByOrderId");
			q.setParameter("orderId", orderId);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileTravelProposal by orderId", pe);
		}
		return result;
	}
	// public static void main(String[] arg) {
	// EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
	// EntityManager em = emf.createEntityManager();
	// em.getTransaction().begin();
	// Query q = em.createNamedQuery("MobileTravelProposal.findByMobileUser");
	// q.setParameter("mobileUserId", "INUSR005001000000006101062018");
	// List<MobileTravelProposal> resultList = q.getResultList();
	// em.flush();
	// }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobilePersonalAccidentProposal updateByPaymentStatus(String orderId) {
		MobilePersonalAccidentProposal result = new MobilePersonalAccidentProposal();
		try {
			Query q = em.createNamedQuery("MobilePersonalAccidentProposal.updateByPaymentStatus");
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
	public MobilePersonalAccidentProposal updateDeleteStuatusByPaymentStatus(String orderId) {
		MobilePersonalAccidentProposal result = new MobilePersonalAccidentProposal();
		boolean deleteStatus = true;
		try {
			Query q = em.createNamedQuery("MobilePersonalAccidentProposal.updateDeleteStatusByPaymentStatus");
			q.setParameter("deleteStatus", deleteStatus);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileTravelProposal", pe);
		}

		return result;
	}

	@Override
	public List<MobilePersonalAccidentProposal> findByFromToDate(String fromDate, String toDate,String convert) {
		List<MobilePersonalAccidentProposal> result = null;
		try {
			Query q = em.createNamedQuery("MobilePersonalAccidentProposal.findByFromToDate");
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("fromDate", new SimpleDateFormat("yyyy-mm-dd").parse(fromDate));
			q.setParameter("toDate", new SimpleDateFormat("yyyy-mm-dd").parse(toDate));
			
				if(("yes").equalsIgnoreCase(convert)) {
				q.setParameter("tConvert", true);
				q.setParameter("fConvert", true);
				}else if(("no").equalsIgnoreCase(convert)) {
				q.setParameter("tConvert", false);
				q.setParameter("fConvert", false);
			}else {
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
	
	public List<MobilePersonalAccidentProposal> findRecordsByResponseStatus(){
		List<MobilePersonalAccidentProposal> result = null;
		try {
			Query q = em.createNamedQuery("MobilePersonalAccidentProposal.findByResponseStatus");
			q.setParameter("responseStatus", ResponseStatus.SUCCESS);
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobilePersonalAccidentProposal by ", pe);
		} 
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status) {
		try {
			Query q = em.createNamedQuery("MobilePersonalAccidentProposal.responseStatusByOrderId");
			q.setParameter("responseStatus", status);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobilePersonalAccidentProposal", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateConvertedStatusByOrderId(PaymentOrderConfirm orderId) {
		try {
			Query q = em.createNamedQuery("MobilePersonalAccidentProposal.updateConvertStatusByOrderId");
			q.setParameter("convert",true);
			q.setParameter("orderId", orderId.getOrderId());
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileTravelProposal", pe);
		}
	}
}
