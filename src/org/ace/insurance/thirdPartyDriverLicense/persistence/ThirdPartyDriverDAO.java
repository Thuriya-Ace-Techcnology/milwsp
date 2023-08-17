package org.ace.insurance.thirdPartyDriverLicense.persistence;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.ace.insurance.common.ProposalStatus;
import org.ace.insurance.thirdPartyDriverLicense.ThirdPartyDriverProposal;
import org.ace.insurance.thirdPartyDriverLicense.persistence.interfaces.IThirdPartyDriverDAO;
import org.ace.java.component.persistence.BasicDAO;
import org.ace.java.component.persistence.exception.DAOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("ThirdPartyDriverDAO")
public class ThirdPartyDriverDAO extends BasicDAO implements IThirdPartyDriverDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ThirdPartyDriverProposal updateByPaymentStatus(String order_id, String paymentDate, String policyNo) {
		ThirdPartyDriverProposal result = new ThirdPartyDriverProposal();
		try {
			Query q = em.createNamedQuery("ThirdPartyDriverProposal.updateByPaymentStatus");
			q.setParameter("policyNo", policyNo);
			q.setParameter("proposalStatus", ProposalStatus.ISSUED);
			q.setParameter("paymentDate", paymentDate);
			q.setParameter("orderId", order_id);
			q.executeUpdate();
		} catch (PersistenceException pe) {
			throw translate("Failed to update ThirdPartyDriver", pe);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ThirdPartyDriverProposal> findByOrderId(String order_id) {
		List<ThirdPartyDriverProposal> result;
		try {
			Query q = em.createNamedQuery("ThirdPartyDriverProposal.findByOrderId");

			q.setParameter("orderId", order_id);
			result = q.getResultList();
		} catch (PersistenceException pe) {
			throw translate("Failed to find Third Party Driver by OrderId", pe);
		}
		return result;
	}
}
