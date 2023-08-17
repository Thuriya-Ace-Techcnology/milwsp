package org.ace.insurance.system.thirdparty.persistence;

/*
 * @author Kyaw Yea Lwin
 * @date 29/06/2020
 */
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.TableName;
import org.ace.insurance.product.PaymentStatus;
import org.ace.insurance.system.thirdparty.ThirdPartyPremiumReceipt;
import org.ace.insurance.system.thirdparty.persistence.interfaces.IThirdPartyPremiumReceiptDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.ace.ws.model.ResponseStatus;
import org.ace.ws.model.TwoCTwoPDTO.PaymentOrderConfirm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("ThirdPartyPremiumReceiptDAO")
public class ThirdPartyPremiumReceiptDAO extends BasicDAO implements IThirdPartyPremiumReceiptDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ThirdPartyPremiumReceipt insert(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException {

		try {
			em.persist(thirdPartyPremiumReceipt);
			insertProcessLog(TableName.THIRDPARTY_PREMIUM_RECEIPT, thirdPartyPremiumReceipt.getId());
			return thirdPartyPremiumReceipt;
		} catch (PersistenceException pe) {
			throw translate("Failed to insert ThirdPartyPremiumReceipt", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ThirdPartyPremiumReceipt update(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException {
		try {
			em.merge(thirdPartyPremiumReceipt);
			em.flush();
			return thirdPartyPremiumReceipt;
		} catch (PersistenceException pe) {
			throw translate("Failed to update ThirdPartyPremiumReceipt", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ThirdPartyPremiumReceipt thirdPartyPremiumReceipt) throws DAOException {
		try {
			thirdPartyPremiumReceipt = em.merge(thirdPartyPremiumReceipt);
			em.remove(thirdPartyPremiumReceipt);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to delete ThirdPartyPremiumReceipt", pe);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ThirdPartyPremiumReceipt findById(String id) throws DAOException {
		ThirdPartyPremiumReceipt result = null;
		try {
			result = em.find(ThirdPartyPremiumReceipt.class, id);
			em.flush();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ThirdPartyPremiumReceipt", pe);
		}
		return result;
	}

	@Override
	public ThirdPartyPremiumReceipt findRecordsByOrderId(String orderId) throws DAOException {
		ThirdPartyPremiumReceipt result = null;
		try {
			Query q = em.createNamedQuery("ThirdPartyPremiumReceipt.findByOrderId");
			q.setParameter("orderId", orderId);
			result = (ThirdPartyPremiumReceipt) q.getSingleResult();
		} catch (NoResultException pe) {
			return result;
		} catch (PersistenceException pe) {
			throw translate("Failed to find ThirdPartyPremiumReceipt", pe);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ThirdPartyPremiumReceipt findByVehicleNo(String vehicle_no) throws DAOException {
		ThirdPartyPremiumReceipt result = null;
		try {
			Query q = em.createQuery(
					"SELECT t FROM ThirdPartyPremiumReceipt t WHERE t.vehicle_no = :vehicle_no and t.period_from = (select MAX(re.period_from) FROM ThirdPartyPremiumReceipt re WHERE re.vehicle_no = :vehicle_no and re.paymentStatus != :paymentStatus) and (t.paymentStatus != :paymentStatus or t.paymentStatus is null)");
			q.setParameter("vehicle_no", vehicle_no);
			q.setParameter("paymentStatus", PaymentStatus.PENDING);
			List<ThirdPartyPremiumReceipt> resultList = q.getResultList();
			result = resultList.stream().findFirst().orElse(null);
			em.flush();
			if (result == null) {
				return result;
			}
		} catch (NoResultException pe) {
			return result;
		} catch (PersistenceException pe) {
			System.out.println(pe.getMessage());
			// throw translate("Failed to find Premium Receipt(Vehiclt No = " + vehicle_no +
			// ")", pe);
			return result;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ThirdPartyPremiumReceipt> findAllThirdPartyPremiumReceipt() throws DAOException {
		List<ThirdPartyPremiumReceipt> result = null;
		try {
			Query q = em.createQuery("SELECT t FROM ThirdPartyPremiumReceipt t ORDER BY t.owner_name ASC");
			result = q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to findAll ThirdPartyPremiumReceipt :", pe);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ThirdPartyPremiumReceipt> findByFromToDate(String fromDate, String toDate, String branch,
			String convert) {
		List<ThirdPartyPremiumReceipt> result = null;
		StringBuilder sb = new StringBuilder(
				"SELECT t FROM ThirdPartyPremiumReceipt t WHERE t.paymentStatus = :paymentStatus and t.buyDate >= :fromDate and t.buyDate <= :toDate ");
		if (branch != null && !branch.equals("")) {
			sb.append("and t.rta_branch = :branch");
		}
		if (convert != null && (convert.equalsIgnoreCase("yes") || convert.equalsIgnoreCase("no"))) {
			sb.append(" and t.isConvert = :convert ");
		}
			sb.append(" and t.order_id is not null ");
		try {
			Query q = em.createQuery(sb.toString());
			q.setParameter("fromDate", fromDate);
			q.setParameter("toDate", toDate);
			q.setParameter("paymentStatus", PaymentStatus.SUCCESS);
			if (branch != null && !branch.equals("")) {
				q.setParameter("branch", branch);
			}
			if (convert != null && convert.equalsIgnoreCase("yes")) {
				q.setParameter("convert", true);
			} else if (convert != null && convert.equalsIgnoreCase("no")) {
				q.setParameter("convert", false);
			}
			result = q.getResultList();
			em.flush();
		} catch (NoResultException pe) {
			return null;
		} catch (PersistenceException pe) {
			throw translate("Failed to find Premium Receipt(FromDate >= " + fromDate + " and toDate <= " + toDate + ")",
					pe);
		}
		return result;
	}

	@Override
	public List<ThirdPartyPremiumReceipt> findReceiptListByVehicleNo(String vehicle_no) throws DAOException {
		List<ThirdPartyPremiumReceipt> result = null;
		try {
			Query q = em.createQuery(
					"SELECT t FROM ThirdPartyPremiumReceipt t WHERE t.vehicle_no = :vehicle_no AND t.paymentStatus != :paymentStatus ORDER BY t.period_from");
			q.setParameter("vehicle_no", vehicle_no);
			q.setParameter("paymentStatus", PaymentStatus.PENDING);
			result = q.getResultList();
			if (result == null) {
				return result;
			}
			em.flush();
		} catch (NoResultException pe) {
			return result;
		} catch (PersistenceException pe) {
			// throw translate("Failed to find Premium Receipt(Vehiclt No = " + vehicle_no +
			// ")", pe);
			return result;
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ThirdPartyPremiumReceipt> findByDateAndVehicleNo(String fromDate, String toDate, String vehicle_no) {
		List<ThirdPartyPremiumReceipt> result = null;
		try {
			Query q = em.createNamedQuery("ThirdPartyPremiumReceipt.findByDateAndVehicleNo");
			q.setParameter("period_from", fromDate);
			// q.setParameter("period_to", toDate);
			q.setParameter("vehicle_no", vehicle_no);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ThirdPartyPremiumReceipt", pe);
		}

		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ThirdPartyPremiumReceipt updateByPaymentStatus(String orderId) {
		ThirdPartyPremiumReceipt result = new ThirdPartyPremiumReceipt();
		try {
			Query q = em.createNamedQuery("ThirdPartyPremiumReceipt.updateByPaymentStatus");
			q.setParameter("paymentStaus", PaymentStatus.SUCCESS);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update ThirdPartyPremiumReceipt", pe);
		}

		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateResponseStatusByOrderId(String orderId, ResponseStatus status) {
		try {
			Query q = em.createNamedQuery("ThirdPartyPremiumReceipt.responseStatusByOrderId");
			q.setParameter("responseStatus", status);
			q.setParameter("orderId", orderId);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update MobileTravelProposal", pe);
		}

	}

	@Override
	public List<ThirdPartyPremiumReceipt> findRecordsByResponseStatus() {
		List<ThirdPartyPremiumReceipt> result = null;
		try {
			Query q = em.createNamedQuery("ThirdPartyPremiumReceipt.findByResponseStatus");
			q.setParameter("responseStatus", ResponseStatus.SUCCESS);
			q.setParameter("paymentStatus", PaymentStatus.SUCCESS);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find ThirdPartyPremiumReceipt by orderId", pe);
		}
		return result;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateConvertedStatusByOrderId(PaymentOrderConfirm orderId) {
		try {
			Query q = em.createNamedQuery("ThirdPartyPremiumReceipt.updateConvertStatusByOrderId");
			q.setParameter("convert", true);
			q.setParameter("orderId", orderId.getOrderId());
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update ThirdPartyPremiumReceipt", pe);
		}
	}

}
