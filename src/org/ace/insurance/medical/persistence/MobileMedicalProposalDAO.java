package org.ace.insurance.medical.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.medical.MobileMedicalProposal;
import org.ace.insurance.medical.persistence.interfaces.IMobileMedicalProposalDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.ace.ws.model.mobileMedicalproposal.InsuredPersonDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("MobileMedicalProposalDAO")
public class MobileMedicalProposalDAO extends BasicDAO implements IMobileMedicalProposalDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(MobileMedicalProposal medicalProposal) throws DAOException {
		try {
			em.persist(medicalProposal);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to insert MobileMedicalProposal", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<MobileMedicalProposal> findByOrderId(String orderId) {
		List<MobileMedicalProposal> result;
		try {
			Query q = em.createNamedQuery("MobileMedicalProposal.findByOrderId");
			q.setParameter("orderId", orderId);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileMedicalProposal by orderId", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileMedicalProposal updateDeleteStuatusByPaymentStatus(String orderId) {
		MobileMedicalProposal result = new MobileMedicalProposal();
		boolean deleteStatus = true;
		try {
			Query q = em.createNamedQuery("MobileMedicalProposal.updateDeleteStatusByPaymentStatus");
			q.setParameter("deleteStatus", deleteStatus);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileMedicalProposal", pe);
		}

		return result;
	}

	@Override
	public List<InsuredPersonDTO> findByMobileUser(String mobileUserId) throws DAOException {

		List<InsuredPersonDTO> resultList = null;
		try {

			StringBuffer buffer = new StringBuffer();
			buffer.append("SELECT NEW " + InsuredPersonDTO.class.getName());
			buffer.append(

					"( pa.initialId,pa.firstName,pa.middleName,pa.lastName, pa.unit,pa.basicPremium,addOn.premium,mtp.proposalStatus,mtp.proposalNo,mtp.policyNo,mtp.submittedDate,mtp.orderId,mtp.transactionFees) ");
			buffer.append("FROM MobileMedicalProposal mtp ");
			buffer.append("LEFT OUTER JOIN mtp.medicalProposalInsuredPersonList pa ");
			buffer.append("LEFT OUTER JOIN pa.insuredPersonAddOnList addOn ");
			buffer.append("WHERE mtp.userId= :mobileUserId and mtp.deleteStatus = 0 ");
			buffer.append("ORDER BY mtp.submittedDate DESC ");
			Query q = em.createQuery(buffer.toString());

			q.setParameter("mobileUserId", mobileUserId);
			resultList = q.getResultList();
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileMedicalInsuredPerson by mobileUser", pe);
		}
		return resultList;
	}

	@Override
	public void update(MobileMedicalProposal mobileMedicalProposal) throws DAOException {
		try {
			em.merge(mobileMedicalProposal);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileMedicalProposal by TransactionID", pe);
		}

	}

	@Override
	public MobileMedicalProposal findHealthProposalByRefNo(String refNo) {
		MobileMedicalProposal result = null;
		try {
			Query q = em.createQuery("SELECT mtp FROM MobileMedicalProposal mtp WHERE mtp.proposalNo = :refNo OR mtp.policyNo=:refNo and mtp.deleteStatus = 0");
			q.setParameter("refNo", refNo);
			result = (MobileMedicalProposal) q.getSingleResult();
			em.flush();
		} catch (NoResultException e) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find Health Proposal By RefNo", pe);
		}
		return result;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MobileMedicalProposal updateByPaymentStatus(String orderId) {
		MobileMedicalProposal result = new MobileMedicalProposal();
		try {
			Query q = em.createNamedQuery("MobileMedicalProposal.updateByPaymentStatus");
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
	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status) {
		try {
			Query q = em.createNamedQuery("MobileMedicalProposal.responseStatusByOrderId");
			q.setParameter("responseStatus", status);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileMedicalProposal", pe);
		}
	}

	@Override
	public List<MobileMedicalProposal> findRecordsByResponseStatus() {
		List<MobileMedicalProposal> result = null;
		try {
			Query q = em.createNamedQuery("MobileMedicalProposal.findByResponseStatus");
			q.setParameter("responseStatus", ResponseStatus.SUCCESS);
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find MobileMedicalProposal by orderId", pe);
		} 
		return result;
	}
	

	@Override
	public List<MobileMedicalProposal> findByFromToDate(String fromDate, String toDate,String convert) {
		List<MobileMedicalProposal> result = null;
		try {
			Query q = em.createNamedQuery("MobileMedicalProposal.findByFromToDate");
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
			throw translate("Failed to find MobileMedicalProposal by orderId", pe);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateConvertedStatusByOrderId(PaymentOrderConfirm orderId) {
		try {
			Query q = em.createNamedQuery("MobileMedicalProposal.updateConvertStatusByOrderId");
			q.setParameter("convert",true);
			q.setParameter("orderId", orderId.getOrderId());
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileMedicalProposal", pe);
		}
	}
}
